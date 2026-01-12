package com.tugalsan.java.core.file.pdf.pdfbox3.server.sign;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;
import module org.bouncycastle.provider;
import module org.bouncycastle.pkix;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.ocsp.CertificateStatus;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;

/**
 * Helper Class for OCSP-Operations with bouncy castle.
 *
 * @author Alexis Suter
 */
public class OcspHelperModified {

    private final X509Certificate issuerCertificate;
    private final Date signDate;
    private final X509Certificate certificateToCheck;
    private final Set<X509Certificate> additionalCerts;
    private final String ocspUrl;
    private DEROctetString encodedNonce;
    private X509Certificate ocspResponderCertificate;
    private final JcaX509CertificateConverter certificateConverter = new JcaX509CertificateConverter();

    // SecureRandom.getInstanceStrong() would be better, but sometimes blocks on Linux
    private static final Random RANDOM = new SecureRandom();

    /**
     * @param checkCertificate Certificate to be OCSP-checked
     * @param signDate the date when the signing took place
     * @param issuerCertificate Certificate of the issuer
     * @param additionalCerts Set of trusted root CA certificates that will be
     * used as "trust anchors" and intermediate CA certificates that will be
     * used as part of the certification chain. All self-signed certificates are
     * considered to be trusted root CA certificates. All the rest are
     * considered to be intermediate CA certificates.
     * @param ocspUrl where to fetch for OCSP
     */
    public OcspHelperModified(TGS_FuncMTU_In1<String> report, X509Certificate checkCertificate, Date signDate, X509Certificate issuerCertificate,
            Set<X509Certificate> additionalCerts, String ocspUrl) {
        this.report = report;
        this.certificateToCheck = checkCertificate;
        this.signDate = signDate;
        this.issuerCertificate = issuerCertificate;
        this.additionalCerts = additionalCerts;
        this.ocspUrl = ocspUrl;
    }
    final private TGS_FuncMTU_In1<String> report;

    /**
     * Get the certificate to be OCSP-checked.
     *
     * @return The certificate to be OCSP-checked.
     */
    X509Certificate getCertificateToCheck() {
        return certificateToCheck;
    }

    /**
     * Performs and verifies the OCSP-Request
     *
     * @return the OCSPResp, when the request was successful, else a
     * corresponding exception will be thrown. Never returns null.
     *
     * @throws IOException
     * @throws OCSPException
     * @throws RevokedCertificateException
     * @throws URISyntaxException
     */
    public OCSPResp getResponseOcsp()
            throws IOException, OCSPException, RevokedCertificateException, URISyntaxException {
        var ocspResponse = performRequest(ocspUrl);
        verifyOcspResponse(ocspResponse);
        return ocspResponse;
    }

    /**
     * Get responder certificate. This is available after
     * {@link #getResponseOcsp()} has been called. This method should be used
     * instead of {@code basicResponse.getCerts()[0]}
     *
     * @return The certificate of the responder.
     */
    public X509Certificate getOcspResponderCertificate() {
        return ocspResponderCertificate;
    }

    /**
     * Verifies the status and the response itself (including nonce), but not
     * the signature.
     *
     * @param ocspResponse to be verified
     * @throws OCSPException
     * @throws RevokedCertificateException
     * @throws IOException if the default security provider can't be
     * instantiated
     */
    private void verifyOcspResponse(OCSPResp ocspResponse)
            throws OCSPException, RevokedCertificateException, IOException {
        verifyRespStatus(ocspResponse);

        var basicResponse = (BasicOCSPResp) ocspResponse.getResponseObject();
        if (basicResponse != null) {
            var responderID = basicResponse.getResponderId().toASN1Primitive();
            // https://tools.ietf.org/html/rfc6960#section-4.2.2.3
            // The basic response type contains:
            // (...)
            // either the name of the responder or a hash of the responder's
            // public key as the ResponderID
            // (...)
            // The responder MAY include certificates in the certs field of
            // BasicOCSPResponse that help the OCSP client verify the responder's
            // signature.
            var name = responderID.getName();
            if (name != null) {
                findResponderCertificateByName(basicResponse, name);
            } else {
                byte[] keyHash = responderID.getKeyHash();
                if (keyHash != null) {
                    findResponderCertificateByKeyHash(basicResponse, keyHash);
                } else {
                    throw new OCSPException("OCSP: basic response must provide name or key hash");
                }
            }

            if (ocspResponderCertificate == null) {
                throw new OCSPException("OCSP: certificate for responder " + name + " not found");
            }

            try {
                SigUtilsModified.checkResponderCertificateUsage(report, ocspResponderCertificate);
            } catch (CertificateParsingException ex) {
                // unlikely to happen because the certificate existed as an object
                report.call(ex.getMessage());
            }
            checkOcspSignature(ocspResponderCertificate, basicResponse);

            var nonceChecked = checkNonce(basicResponse);

            var responses = basicResponse.getResponses();
            if (responses.length != 1) {
                throw new OCSPException(
                        "OCSP: Received " + responses.length + " responses instead of 1!");
            }

            var resp = responses[0];
            var status = resp.getCertStatus();

            if (!nonceChecked) {
                // https://tools.ietf.org/html/rfc5019
                // fall back to validating the OCSPResponse based on time
                checkOcspResponseFresh(resp);
            }

            if (status instanceof RevokedStatus revokedStatus) {
                if (revokedStatus.getRevocationTime().compareTo(signDate) <= 0) {
                    throw new RevokedCertificateException(
                            "OCSP: Certificate is revoked since "
                            + revokedStatus.getRevocationTime(),
                            revokedStatus.getRevocationTime());
                }
                report.call("The certificate was revoked after signing by OCSP " + ocspUrl
                        + " on " + revokedStatus.getRevocationTime());
            } else if (status != CertificateStatus.GOOD) {
                throw new OCSPException("OCSP: Status of Cert is unknown");
            }
        }
    }

    private byte[] getKeyHashFromCertHolder(X509CertificateHolder certHolder) {
        // https://tools.ietf.org/html/rfc2560#section-4.2.1
        // KeyHash ::= OCTET STRING -- SHA-1 hash of responder's public key
        //         -- (i.e., the SHA-1 hash of the value of the
        //         -- BIT STRING subjectPublicKey [excluding
        //         -- the tag, length, and number of unused
        //         -- bits] in the responder's certificate)

        // code below inspired by org.bouncycastle.cert.ocsp.CertificateID.createCertID()
        // tested with SO52757037-Signed3-OCSP-with-KeyHash.pdf
        var info = certHolder.getSubjectPublicKeyInfo();
        try {
            return MessageDigest.getInstance("SHA-1").digest(info.getPublicKeyData().getBytes());
        } catch (NoSuchAlgorithmException ex) {
            // should not happen
            report.call("SHA-1 Algorithm not found: " + ex.getMessage());
            return new byte[0];
        }
    }

    private void findResponderCertificateByKeyHash(BasicOCSPResp basicResponse, byte[] keyHash)
            throws IOException {
        var certHolders = basicResponse.getCerts();
        for (var certHolder : certHolders) {
            var digest = getKeyHashFromCertHolder(certHolder);
            if (Arrays.equals(keyHash, digest)) {
                try {
                    ocspResponderCertificate = certificateConverter.getCertificate(certHolder);
                    return;
                } catch (CertificateException ex) {
                    // unlikely to happen because the certificate existed as an object
                    report.call(ex.getMessage());
                }
                break;
            }
        }

        // DO NOT use the certificate found in additionalCerts first. One file had a
        // responder certificate in the PDF itself with SHA1withRSA algorithm, but
        // the responder delivered a different (newer, more secure) certificate
        // with SHA256withRSA (tried with QV_RCA1_RCA3_CPCPS_V4_11.pdf)
        // https://www.quovadisglobal.com/~/media/Files/Repository/QV_RCA1_RCA3_CPCPS_V4_11.ashx
        for (var cert : additionalCerts) {
            try {
                var digest = getKeyHashFromCertHolder(new X509CertificateHolder(cert.getEncoded()));
                if (Arrays.equals(keyHash, digest)) {
                    ocspResponderCertificate = cert;
                    return;
                }
            } catch (CertificateEncodingException ex) {
                // unlikely to happen because the certificate existed as an object
                report.call(ex.getMessage());
            }
        }
    }

    private void findResponderCertificateByName(BasicOCSPResp basicResponse, X500Name name) {
        var certHolders = basicResponse.getCerts();
        for (var certHolder : certHolders) {
            if (name.equals(certHolder.getSubject())) {
                try {
                    ocspResponderCertificate = certificateConverter.getCertificate(certHolder);
                    return;
                } catch (CertificateException ex) {
                    // unlikely to happen because the certificate existed as an object
                    report.call(ex.getMessage());
                }
            }
        }

        // DO NOT use the certificate found in additionalCerts first. One file had a
        // responder certificate in the PDF itself with SHA1withRSA algorithm, but
        // the responder delivered a different (newer, more secure) certificate
        // with SHA256withRSA (tried with QV_RCA1_RCA3_CPCPS_V4_11.pdf)
        // https://www.quovadisglobal.com/~/media/Files/Repository/QV_RCA1_RCA3_CPCPS_V4_11.ashx
        for (var cert : additionalCerts) {
            var certSubjectName = new X500Name(cert.getSubjectX500Principal().getName());
            if (certSubjectName.equals(name)) {
                ocspResponderCertificate = cert;
                return;
            }
        }
    }

    private void checkOcspResponseFresh(SingleResp resp) throws OCSPException {
        // https://tools.ietf.org/html/rfc5019
        // Clients MUST check for the existence of the nextUpdate field and MUST
        // ensure the current time, expressed in GMT time as described in
        // Section 2.2.4, falls between the thisUpdate and nextUpdate times.  If
        // the nextUpdate field is absent, the client MUST reject the response.

        var curDate = Calendar.getInstance().getTime();

        var thisUpdate = resp.getThisUpdate();
        if (thisUpdate == null) {
            throw new OCSPException("OCSP: thisUpdate field is missing in response (RFC 5019 2.2.4.)");
        }
        var nextUpdate = resp.getNextUpdate();
        if (nextUpdate == null) {
            throw new OCSPException("OCSP: nextUpdate field is missing in response (RFC 5019 2.2.4.)");
        }
        if (curDate.compareTo(thisUpdate) < 0) {
            report.call(curDate + " < " + thisUpdate);
            throw new OCSPException("OCSP: current date < thisUpdate field (RFC 5019 2.2.4.)");
        }
        if (curDate.compareTo(nextUpdate) > 0) {
            report.call(curDate + " > " + nextUpdate);
            throw new OCSPException("OCSP: current date > nextUpdate field (RFC 5019 2.2.4.)");
        }
        report.call("OCSP response is fresh");
    }

    /**
     * Checks whether the OCSP response is signed by the given certificate.
     *
     * @param certificate the certificate to check the signature
     * @param basicResponse OCSP response containing the signature
     * @throws OCSPException when the signature is invalid or could not be
     * checked
     * @throws IOException if the default security provider can't be
     * instantiated
     */
    private void checkOcspSignature(X509Certificate certificate, BasicOCSPResp basicResponse)
            throws OCSPException, IOException {
        try {
            var verifier = new JcaContentVerifierProviderBuilder()
                    .setProvider(SecurityProvider.getProvider()).build(certificate);

            if (!basicResponse.isSignatureValid(verifier)) {
                throw new OCSPException("OCSP-Signature is not valid!");
            }
        } catch (OperatorCreationException e) {
            throw new OCSPException("Error checking Ocsp-Signature", e);
        }
    }

    /**
     * Checks if the nonce in the response matches.
     *
     * @param basicResponse Response to be checked
     * @return true if the nonce is present and matches, false if nonce is
     * missing.
     * @throws OCSPException if the nonce is different
     */
    private boolean checkNonce(BasicOCSPResp basicResponse) throws OCSPException {
        var nonceExt = basicResponse.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
        if (nonceExt != null) {
            var responseNonceString = (DEROctetString) nonceExt.getExtnValue();
            if (!responseNonceString.equals(encodedNonce)) {
                throw new OCSPException("Different nonce found in response!");
            } else {
                report.call("Nonce is good");
                return true;
            }
        }
        // https://tools.ietf.org/html/rfc5019
        // Clients that opt to include a nonce in the
        // request SHOULD NOT reject a corresponding OCSPResponse solely on the
        // basis of the nonexistent expected nonce, but MUST fall back to
        // validating the OCSPResponse based on time.
        return false;
    }

    /**
     * Performs the OCSP-Request, with given data.
     *
     * @param urlString URL of OCSP service.
     * @return the OCSPResp, that has been fetched from the ocspUrl
     * @throws IOException
     * @throws OCSPException
     * @throws URISyntaxException
     */
    private OCSPResp performRequest(String urlString)
            throws IOException, OCSPException, URISyntaxException {
        var request = generateOCSPRequest();
        var url = new URI(urlString).toURL();
        var httpConnection = (HttpURLConnection) url.openConnection();
        try {
            httpConnection.setRequestProperty("Content-Type", "application/ocsp-request");
            httpConnection.setRequestProperty("Accept", "application/ocsp-response");
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);
            try (var out = httpConnection.getOutputStream()) {
                out.write(request.getEncoded());
            }

            var responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
                    || responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    || responseCode == HttpURLConnection.HTTP_SEE_OTHER) {
                var location = httpConnection.getHeaderField("Location");
                if (urlString.startsWith("http://")
                        && location.startsWith("https://")
                        && urlString.substring(7).equals(location.substring(8))) {
                    // redirection from http:// to https://
                    // change this code if you want to be more flexible (but think about security!)
                    report.call("redirection to " + location + " followed");
                    return performRequest(location);
                } else {
                    report.call("redirection to " + location + " ignored");
                }
            }
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("OCSP: Could not access url, ResponseCode "
                        + httpConnection.getResponseCode() + ": "
                        + httpConnection.getResponseMessage());
            }
            // Get response
            try (var in = (InputStream) httpConnection.getContent()) {
                return new OCSPResp(in);
            }
        } finally {
            httpConnection.disconnect();
        }
    }

    /**
     * Helper method to verify response status.
     *
     * @param resp OCSP response
     * @throws OCSPException if the response status is not ok
     */
    public void verifyRespStatus(OCSPResp resp) throws OCSPException {
        var statusInfo = "";
        if (resp != null) {
            var status = resp.getStatus();
            switch (status) {
                case OCSPResponseStatus.INTERNAL_ERROR -> {
                    statusInfo = "INTERNAL_ERROR";
                    report.call("An internal error occurred in the OCSP Server!");
                }
                case OCSPResponseStatus.MALFORMED_REQUEST -> {
                    // This happened when the "critical" flag was used for extensions
                    // on a responder known by the committer of this comment.
                    statusInfo = "MALFORMED_REQUEST";
                    report.call("Your request did not fit the RFC 2560 syntax!");
                }
                case OCSPResponseStatus.SIG_REQUIRED -> {
                    statusInfo = "SIG_REQUIRED";
                    report.call("Your request was not signed!");
                }
                case OCSPResponseStatus.TRY_LATER -> {
                    statusInfo = "TRY_LATER";
                    report.call("The server was too busy to answer you!");
                }
                case OCSPResponseStatus.UNAUTHORIZED -> {
                    statusInfo = "UNAUTHORIZED";
                    report.call("The server could not authenticate you!");
                }
                case OCSPResponseStatus.SUCCESSFUL -> {
                }
                default -> {
                    statusInfo = "UNKNOWN";
                    report.call("Unknown OCSPResponse status code! " + status);
                }
            }
        }
        if (resp == null || resp.getStatus() != OCSPResponseStatus.SUCCESSFUL) {
            throw new OCSPException("OCSP response unsuccessful, status: " + statusInfo);
        }
    }

    /**
     * Generates an OCSP request and generates the <code>CertificateID</code>.
     *
     * @return OCSP request, ready to fetch data
     * @throws OCSPException
     * @throws IOException
     */
    private OCSPReq generateOCSPRequest() throws OCSPException, IOException {
        Security.addProvider(SecurityProvider.getProvider());

        // Generate the ID for the certificate we are looking for
        CertificateID certId;
        try {
            certId = new CertificateID(new SHA1DigestCalculator(report),
                    new JcaX509CertificateHolder(issuerCertificate),
                    certificateToCheck.getSerialNumber());
        } catch (CertificateEncodingException e) {
            throw new IOException("Error creating CertificateID with the Certificate encoding", e);
        }

        // https://tools.ietf.org/html/rfc2560#section-4.1.2
        // Support for any specific extension is OPTIONAL. The critical flag
        // SHOULD NOT be set for any of them.
        var responseExtension = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_response,
                false, new DLSequence(OCSPObjectIdentifiers.id_pkix_ocsp_basic).getEncoded());

        encodedNonce = new DEROctetString(new DEROctetString(create16BytesNonce()));
        var nonceExtension = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, false,
                encodedNonce);

        var builder = new OCSPReqBuilder();
        builder.setRequestExtensions(
                new Extensions(new Extension[]{responseExtension, nonceExtension}));
        builder.addRequest(certId);
        return builder.build();
    }

    private byte[] create16BytesNonce() {
        var nonce = new byte[16];
        RANDOM.nextBytes(nonce);
        return nonce;
    }

    /**
     * Class to create SHA-1 Digest, used for creation of CertificateID.
     */
    private static class SHA1DigestCalculator implements DigestCalculator {

        public SHA1DigestCalculator(TGS_FuncMTU_In1<String> report) {
            this.report = report;
        }
        final private TGS_FuncMTU_In1<String> report;

        private final ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        @Override
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }

        @Override
        public OutputStream getOutputStream() {
            return bOut;
        }

        @Override
        public byte[] getDigest() {
            var bytes = bOut.toByteArray();
            bOut.reset();

            try {
                var md = MessageDigest.getInstance("SHA-1");
                return md.digest(bytes);
            } catch (NoSuchAlgorithmException ex) {
                // should not happen
                report.call("SHA-1 Algorithm not found" + ex.getMessage());
                return new byte[0];
            }
        }
    }
}
