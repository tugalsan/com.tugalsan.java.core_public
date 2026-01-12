package com.tugalsan.java.core.file.pdf.pdfbox3.server.sign;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.string;
import module pdfbox.examples;
import module org.bouncycastle.provider;
import module org.bouncycastle.pkix;
import org.bouncycastle.asn1.x509.Extension;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.*;

/**
 * Copied from Apache CXF 2.4.9, initial version:
 * https://svn.apache.org/repos/asf/cxf/tags/cxf-2.4.9/distribution/src/main/release/samples/sts_issue_operation/src/main/java/demo/sts/provider/cert/
 *
 */
public final class CertificateVerifierModified {

    private CertificateVerifierModified() {

    }

    /**
     * Attempts to build a certification chain for given certificate and to
     * verify it. Relies on a set of root CA certificates and intermediate
     * certificates that will be used for building the certification chain. The
     * verification process assumes that all self-signed certificates in the set
     * are trusted root CA certificates and all other certificates in the set
     * are intermediate certificates.
     *
     * @param cert - certificate for validation
     * @param additionalCerts - set of trusted root CA certificates that will be
     * used as "trust anchors" and intermediate CA certificates that will be
     * used as part of the certification chain. All self-signed certificates are
     * considered to be trusted root CA certificates. All the rest are
     * considered to be intermediate CA certificates.
     * @param verifySelfSignedCert true if a self-signed certificate is
     * accepted, false if not.
     * @param signDate the date when the signing took place
     * @return the certification chain (if verification is successful)
     * @throws CertificateVerificationException - if the certification is not
     * successful (e.g. certification path cannot be built or some certificate
     * in the chain is expired or CRL checks are failed)
     */
    public static record PKIXCertPathBuilderResultWithTrustAnchorsStatus(PKIXCertPathBuilderResult result, boolean isContainsLocal, boolean isSelfSigned, boolean isLetsEncrypt, boolean isFreeTSA, boolean isISRG) {

    }

    public static List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> verifyCertificate(TGS_FuncMTU_In1<String> report,
            X509Certificate cert, Set<X509Certificate> additionalCerts,
            boolean verifySelfSignedCert, Date signDate, List<Certificate> trustedLocalCertificates)
            throws CertificateVerificationException {
        List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> results = new ArrayList();
        try {
            var isSelfSigned = false;
            var isContainsLocal = false;
            var isLetsEncript = false;
            var isFreeTSA = false;
            var isISRG = false;

            // Check for self-signed certificate
            if (!verifySelfSignedCert && isSelfSigned(report, cert)) {
                throw new CertificateVerificationException("The certificate is self-signed.");
            }

            Set<X509Certificate> certSet = new HashSet<>(additionalCerts);

            // Download extra certificates. However, each downloaded certificate can lead to
            // more extra certificates, e.g. with the file from PDFBOX-4091, which has
            // an incomplete chain.
            // You can skip this block if you know that the certificate chain is complete
            Set<X509Certificate> certsToTrySet = new HashSet<>();
            certsToTrySet.add(cert);
            certsToTrySet.addAll(additionalCerts);
            int downloadSize = 0;
            while (!certsToTrySet.isEmpty()) {
                Set<X509Certificate> nextCertsToTrySet = new HashSet<>();
                for (X509Certificate tryCert : certsToTrySet) {
                    Set<X509Certificate> downloadedExtraCertificatesSet
                            = CertificateVerifierModified.downloadExtraCertificates(report, tryCert);
                    for (X509Certificate downloadedCertificate : downloadedExtraCertificatesSet) {
                        if (!certSet.contains(downloadedCertificate)) {
                            nextCertsToTrySet.add(downloadedCertificate);
                            certSet.add(downloadedCertificate);
                            downloadSize++;
                        }
                    }
                }
                certsToTrySet = nextCertsToTrySet;
            }
            if (downloadSize > 0) {
                report.call("CA issuers: " + downloadSize + " downloaded certificate(s) are new");
            }

            // Prepare a set of trust anchors (set of root CA certificates)
            // and a set of intermediate certificates
            Set<X509Certificate> intermediateCerts = new HashSet<>();
            Set<TrustAnchor> trustAnchors = new HashSet<>();
            var manual_add_subjectTsaFree = "Subject: C=DE, ST=Bayern, L=Wuerzburg, EMAILADDRESS=busilezas@gmail.com, CN=www.freetsa.org, OU=Root CA, O=Free TSA";
            var manual_add_issuerTsaFree = "Issuer: C=DE, ST=Bayern, L=Wuerzburg, EMAILADDRESS=busilezas@gmail.com, CN=www.freetsa.org, OU=Root CA, O=Free TSA";
            var manual_add_subjectLetsEncrypt = "Subject: CN=E5, O=Let's Encrypt, C=US";
            var manual_add_issuerLetsEncrypt = "Issuer: CN=ISRG Root X1, O=Internet Security Research Group, C=US";
            var manual_add_subjectISRG = "Subject: CN=ISRG Root X1, O=Internet Security Research Group, C=US";
            var manual_add_issuerISRG = "Issuer: CN=ISRG Root X1, O=Internet Security Research Group, C=US";
            TGS_FuncMTU_In1<List<String>> printLines = lines -> {
                var indent = 1;
                for (var line : lines) {
                    if (line.contains("[") && line.contains("]")) {
                        report.call(" - ".repeat(indent) + line);
                    } else if (line.contains("[")) {
                        report.call(" - ".repeat(indent) + line);
                        indent++;
                    } else if (line.contains("]")) {
                        indent--;
                        report.call(" - ".repeat(indent) + line);
                    } else {
                        report.call(" - ".repeat(indent) + line);
                    }
                    if (indent < 1) {
                        indent = 1;
                    }
                }
            };
            for (var additionalCert : certSet) {
                var existsAlready = trustAnchors.stream().anyMatch(t -> t.getTrustedCert().equals(additionalCert));
                if (existsAlready) {
                    continue;
                }
                var additionalCert_isPresent = trustedLocalCertificates.stream().anyMatch(t -> t.equals(additionalCert));
                if (isSelfSigned(report, additionalCert)) {
                    var additionalCertStr = additionalCert.toString();
                    IO.println("############additionalCert.toString()##########");
                    IO.println(additionalCertStr);
                    IO.println("###############################################");
                    if (additionalCert_isPresent) {
                        report.call("! DETECTED LOCALLY TRUSTED, ADDED TO TRUST ANCHORS MANUALLY !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isContainsLocal = true;
                    } else if (additionalCertStr.contains(manual_add_subjectTsaFree) && additionalCertStr.contains(manual_add_issuerTsaFree)) {
                        report.call("! DETECTED FREE TSA, ADDED TO TRUST ANCHORS MANUALLY, THOUGHT IT IS TRUSTED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isFreeTSA = true;
                    } else if (additionalCertStr.contains(manual_add_subjectISRG) && additionalCertStr.contains(manual_add_issuerISRG)) {
                        report.call("! DETECTED ISRG, ADDED TO TRUST ANCHORS MANUALLY, THOUGHT IT IS TRUSTED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isISRG = true;
                    } else if (additionalCertStr.contains(manual_add_subjectLetsEncrypt) && additionalCertStr.contains(manual_add_issuerLetsEncrypt)) {
                        report.call("! DETECTED Let's Encrypt, ADDED TO TRUST ANCHORS MANUALLY, THOUGHT IT IS TRUSTED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isLetsEncript = true;
                    } else {
                        report.call("! DETECTED SELF SIGNED, ADDED TO TRUST ANCHORS MANUALLY, THOUGHT IT IS TRUSTED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isSelfSigned = true;
                    }
                    trustAnchors.add(new TrustAnchor(additionalCert, null));
                } else {
                    var additionalCertStr = additionalCert.toString();
                    IO.println("############additionalCert.toString()##########");
                    IO.println(additionalCertStr);
                    IO.println("###############################################");

                    if (additionalCert_isPresent) {
                        report.call("! DETECTED LOCALLY TRUSTED, ADDED TO INTERMEDIATE ANCHORS MANUALLY !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isContainsLocal = true;
                    } else if (additionalCertStr.contains(manual_add_subjectTsaFree) && additionalCertStr.contains(manual_add_issuerTsaFree)) {
                        report.call("! DETECTED FREE TSA, ADDED TO INTERMEDIATE ANCHORS MANUALLY, THOUGHT IT IS TRUETED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isLetsEncript = true;
                    } else if (additionalCertStr.contains(manual_add_subjectISRG) && additionalCertStr.contains(manual_add_issuerISRG)) {
                        report.call("! DETECTED TSA, ADDED TO INTERMEDIATE ANCHORS MANUALLY, THOUGHT IT IS TRUETED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isLetsEncript = true;
                    } else if (additionalCertStr.contains(manual_add_subjectLetsEncrypt) && additionalCertStr.contains(manual_add_issuerLetsEncrypt)) {
                        report.call("! DETECTED Let's Encrypt, ADDED TO INTERMEDIATE ANCHORS MANUALLY, THOUGHT IT IS TRUETED !");
                        var lines = TGS_StringUtils.jre().toList_ln(additionalCertStr).stream().filter(line
                                -> line.contains("Subject: ") || line.contains("Issuer: ")
                        ).toList();
                        printLines.run(lines);
                        isLetsEncript = true;
                    }
                    intermediateCerts.add(additionalCert);
                }
            }

            if (trustAnchors.isEmpty()) {
                throw new CertificateVerificationException("No root certificate in the chain");
            }

            // Attempt to build the certification chain and verify it
            var verifiedCertChain = verifyCertificate(cert, trustAnchors, intermediateCerts, signDate);

            report.call("Certification chain verified successfully up to this root: " + verifiedCertChain.getTrustAnchor().getTrustedCert().getSubjectX500Principal());

            var results_for_revoc = checkRevocations(report, cert, certSet, signDate, trustedLocalCertificates);
            results.addAll(results_for_revoc);
            results.add(new PKIXCertPathBuilderResultWithTrustAnchorsStatus(verifiedCertChain, isContainsLocal, isSelfSigned, isLetsEncript, isFreeTSA, isISRG));
            return results;
        } catch (CertPathBuilderException certPathEx) {
            throw new CertificateVerificationException(
                    "Error building certification path: "
                    + cert.getSubjectX500Principal(), certPathEx);
        } catch (CertificateVerificationException cvex) {
            throw cvex;
        } catch (IOException | URISyntaxException
                | GeneralSecurityException | RevokedCertificateException | OCSPException ex) {
            throw new CertificateVerificationException(
                    "Error verifying the certificate: "
                    + cert.getSubjectX500Principal(), ex);
        }
    }

    private static List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> checkRevocations(TGS_FuncMTU_In1<String> report, X509Certificate cert,
            Set<X509Certificate> additionalCerts,
            Date signDate, List<Certificate> trustedLocalCertificates)
            throws IOException, CertificateVerificationException, OCSPException,
            RevokedCertificateException, GeneralSecurityException, URISyntaxException {
        List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> results = new ArrayList();
        if (isSelfSigned(report, cert)) {
            // root, we're done
            return results;
        }
        for (var additionalCert : additionalCerts) {
            try {
                cert.verify(additionalCert.getPublicKey(), SecurityProvider.getProvider());
                var results_revoc_with_issuer = checkRevocationsWithIssuer(report, cert, additionalCert, additionalCerts, signDate, trustedLocalCertificates);
                results.addAll(results_revoc_with_issuer);
                // there can be several issuers
            } catch (GeneralSecurityException ex) {
                // not the issuer
            }
        }
        return results;
    }

    private static List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> checkRevocationsWithIssuer(TGS_FuncMTU_In1<String> report, X509Certificate cert, X509Certificate issuerCert,
            Set<X509Certificate> additionalCerts, Date signDate, List<Certificate> trustedLocalCertificates)
            throws OCSPException, CertificateVerificationException, RevokedCertificateException,
            GeneralSecurityException, IOException, URISyntaxException {
        List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> results = new ArrayList();
        // Try checking the certificate through OCSP (faster than CRL)
        var ocspURL = extractOCSPURL(report, cert);
        if (ocspURL != null) {
            OcspHelperModified ocspHelper = new OcspHelperModified(report, cert, signDate, issuerCert, additionalCerts, ocspURL);
            try {
                var results_verifyOCSP = verifyOCSP(report, ocspHelper, additionalCerts, trustedLocalCertificates);
                results.addAll(results_verifyOCSP);
            } catch (IOException | OCSPException ex) {
                // IOException happens with 021496.pdf because OCSP responder no longer exists
                // OCSPException happens with QV_RCA1_RCA3_CPCPS_V4_11.pdf
                report.call("Exception trying OCSP, will try CRL: " + ex.getMessage());
                report.call("Certificate# to check: " + cert.getSerialNumber().toString(16));
                CRLVerifier.verifyCertificateCRLs(cert, signDate, additionalCerts);
            }
        } else {
            report.call("OCSP not available, will try CRL");

            // Check whether the certificate is revoked by the CRL
            // given in its CRL distribution point extension
            CRLVerifier.verifyCertificateCRLs(cert, signDate, additionalCerts);
        }

        // now check the issuer
        var results_revoc = checkRevocations(report, issuerCert, additionalCerts, signDate, trustedLocalCertificates);
        results.addAll(results_revoc);
        return results;
    }

    /**
     * Checks whether given X.509 certificate is self-signed.
     *
     * @param cert The X.509 certificate to check.
     * @return true if the certificate is self-signed, false if error or not
     * self-signed.
     */
    public static boolean isSelfSigned(TGS_FuncMTU_In1<String> report, X509Certificate cert) {
        try {
            // Try to verify certificate signature with its own public key
            var key = cert.getPublicKey();
            cert.verify(key, SecurityProvider.getProvider());
            return true;
        } catch (GeneralSecurityException | IllegalArgumentException | IOException ex) {
            // Invalid signature --> not self-signed
            report.call("isSelfSigned?: Couldn't get signature information - returning false: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Download extra certificates from the URI mentioned in id-ad-caIssuers in
     * the "authority information access" extension. The method is lenient, i.e.
     * catches all exceptions.
     *
     * @param ext an X509 object that can have extensions.
     *
     * @return a certificate set, never null.
     */
    public static Set<X509Certificate> downloadExtraCertificates(TGS_FuncMTU_In1<String> report, X509Extension ext) {
        // https://tools.ietf.org/html/rfc2459#section-4.2.2.1
        // https://tools.ietf.org/html/rfc3280#section-4.2.2.1
        // https://tools.ietf.org/html/rfc4325
        Set<X509Certificate> resultSet = new HashSet<>();
        var authorityExtensionValue = ext.getExtensionValue(Extension.authorityInfoAccess.getId());
        if (authorityExtensionValue == null) {
            return resultSet;
        }
        ASN1Primitive asn1Prim;
        try {
            asn1Prim = JcaX509ExtensionUtils.parseExtensionValue(authorityExtensionValue);
        } catch (IOException ex) {
            report.call(ex.getMessage());
            return resultSet;
        }
        if (!(asn1Prim instanceof ASN1Sequence)) {
            report.call("ASN1Sequence expected, got " + asn1Prim.getClass().getSimpleName());
            return resultSet;
        }
        var asn1Seq = (ASN1Sequence) asn1Prim;
        Enumeration<?> objects = asn1Seq.getObjects();
        while (objects.hasMoreElements()) {
            // AccessDescription
            var obj = (ASN1Sequence) objects.nextElement();
            var oid = obj.getObjectAt(0);
            if (!X509ObjectIdentifiers.id_ad_caIssuers.equals(oid)) {
                continue;
            }
            var location = (ASN1TaggedObject) obj.getObjectAt(1);
            var uri = (ASN1OctetString) location.getBaseObject();
            var urlString = new String(uri.getOctets());
            report.call("CA issuers URL: " + urlString);
            try (var in = SigUtilsModified.openURL(report, urlString)) {
                var certFactory = CertificateFactory.getInstance("X.509");
                Collection<? extends Certificate> altCerts = certFactory.generateCertificates(in);
                altCerts.forEach(altCert -> resultSet.add((X509Certificate) altCert));
                report.call("CA issuers URL: " + altCerts.size() + " certificate(s) downloaded");
            } catch (IOException | URISyntaxException ex) {
                report.call(urlString + " failure: " + ex.getMessage());
            } catch (CertificateException ex) {
                report.call(ex.getMessage());
            }
        }
        report.call("CA issuers: Downloaded " + resultSet.size() + " certificate(s) total");
        return resultSet;
    }

    /**
     * Attempts to build a certification chain for given certificate and to
     * verify it. Relies on a set of root CA certificates (trust anchors) and a
     * set of intermediate certificates (to be used as part of the chain).
     *
     * @param cert - certificate for validation
     * @param trustAnchors - set of trust anchors
     * @param intermediateCerts - set of intermediate certificates
     * @param signDate the date when the signing took place
     * @return the certification chain (if verification is successful)
     * @throws GeneralSecurityException - if the verification is not successful
     * (e.g. certification path cannot be built or some certificate in the chain
     * is expired)
     */
    private static PKIXCertPathBuilderResult verifyCertificate(
            X509Certificate cert, Set<TrustAnchor> trustAnchors,
            Set<X509Certificate> intermediateCerts, Date signDate)
            throws GeneralSecurityException {
        // Create the selector that specifies the starting certificate
        var selector = new X509CertSelector();
        selector.setCertificate(cert);

        // Configure the PKIX certificate builder algorithm parameters
        var pkixParams = new PKIXBuilderParameters(trustAnchors, selector);

        // Disable CRL checks (this is done manually as additional step)
        pkixParams.setRevocationEnabled(false);

        // not doing this brings
        // "SunCertPathBuilderException: unable to find valid certification path to requested target"
        // (when using -Djava.security.debug=certpath: "critical policy qualifiers present in certificate")
        // for files like 021496.pdf that have the "Adobe CDS Certificate Policy" 1.2.840.113583.1.2.1
        // CDS = "Certified Document Services"
        // https://www.adobe.com/misc/pdfs/Adobe_CDS_CP.pdf
        pkixParams.setPolicyQualifiersRejected(false);
        // However, maybe there is still work to do:
        // "If the policyQualifiersRejected flag is set to false, it is up to the application
        // to validate all policy qualifiers in this manner in order to be PKIX compliant."

        pkixParams.setDate(signDate);

        // Specify a list of intermediate certificates
        var intermediateCertStore = CertStore.getInstance("Collection",
                new CollectionCertStoreParameters(intermediateCerts));
        pkixParams.addCertStore(intermediateCertStore);

        // Build and verify the certification chain
        // If this doesn't work although it should, it can be debugged
        // by starting java with -Djava.security.debug=certpath
        // see also
        // https://docs.oracle.com/javase/8/docs/technotes/guides/security/troubleshooting-security.html
        var builder = CertPathBuilder.getInstance("PKIX");
        return (PKIXCertPathBuilderResult) builder.build(pkixParams);
    }

    /**
     * Extract the OCSP URL from an X.509 certificate if available.
     *
     * @param cert X.509 certificate
     * @return the URL of the OCSP validation service
     * @throws IOException
     */
    private static String extractOCSPURL(TGS_FuncMTU_In1<String> report, X509Certificate cert) throws IOException {
        var authorityExtensionValue = cert.getExtensionValue(Extension.authorityInfoAccess.getId());
        if (authorityExtensionValue != null) {
            // copied from CertInformationHelper.getAuthorityInfoExtensionValue()
            // DRY refactor should be done some day
            var asn1Seq = (ASN1Sequence) JcaX509ExtensionUtils.parseExtensionValue(authorityExtensionValue);
            Enumeration<?> objects = asn1Seq.getObjects();
            while (objects.hasMoreElements()) {
                // AccessDescription
                var obj = (ASN1Sequence) objects.nextElement();
                var oid = obj.getObjectAt(0);
                // accessLocation
                var location = (ASN1TaggedObject) obj.getObjectAt(1);
                if (X509ObjectIdentifiers.id_ad_ocsp.equals(oid)
                        && location.getTagNo() == GeneralName.uniformResourceIdentifier) {
                    var url = (ASN1OctetString) location.getBaseObject();
                    var ocspURL = new String(url.getOctets());
                    report.call("OCSP URL: " + ocspURL);
                    return ocspURL;
                }
            }
        }
        return null;
    }

    /**
     * Verify whether the certificate has been revoked at signing date, and
     * verify whether the certificate of the responder has been revoked now.
     *
     * @param ocspHelper the OCSP helper.
     * @param additionalCerts
     * @throws RevokedCertificateException
     * @throws IOException
     * @throws URISyntaxException
     * @throws OCSPException
     * @throws CertificateVerificationException
     */
    private static List<PKIXCertPathBuilderResultWithTrustAnchorsStatus> verifyOCSP(TGS_FuncMTU_In1<String> report, OcspHelperModified ocspHelper, Set<X509Certificate> additionalCerts, List<Certificate> trustedLocalCertificates)
            throws RevokedCertificateException, IOException, OCSPException,
            CertificateVerificationException, URISyntaxException {
        var now = Calendar.getInstance().getTime();
        OCSPResp ocspResponse;
        ocspResponse = ocspHelper.getResponseOcsp();
        if (ocspResponse.getStatus() != OCSPResp.SUCCESSFUL) {
            throw new CertificateVerificationException("OCSP check not successful, status: "
                    + ocspResponse.getStatus());
        }
        report.call("OCSP check successful");

        var basicResponse = (BasicOCSPResp) ocspResponse.getResponseObject();
        var ocspResponderCertificate = ocspHelper.getOcspResponderCertificate();
        if (ocspResponderCertificate.getExtensionValue(OCSPObjectIdentifiers.id_pkix_ocsp_nocheck.getId()) != null) {
            // https://tools.ietf.org/html/rfc6960#section-4.2.2.2.1
            // A CA may specify that an OCSP client can trust a responder for the
            // lifetime of the responder's certificate.  The CA does so by
            // including the extension id-pkix-ocsp-nocheck.
            report.call("Revocation check of OCSP responder certificate skipped (id-pkix-ocsp-nocheck is set)");
            return new ArrayList();
        }

        if (ocspHelper.getCertificateToCheck().equals(ocspResponderCertificate)) {
            report.call("OCSP responder certificate is identical to certificate to check");
            return new ArrayList();
        }

        report.call("Check of OCSP responder certificate");
        Set<X509Certificate> additionalCerts2 = new HashSet<>(additionalCerts);
        var certificateConverter = new JcaX509CertificateConverter();
        for (var certHolder : basicResponse.getCerts()) {
            try {
                var cert = certificateConverter.getCertificate(certHolder);
                if (!ocspResponderCertificate.equals(cert)) {
                    additionalCerts2.add(cert);
                }
            } catch (CertificateException ex) {
                // unlikely to happen because the certificate existed as an object
                report.call(ex.getMessage());
            }
        }
        var results = CertificateVerifierModified.verifyCertificate(report, ocspResponderCertificate, additionalCerts2, true, now, trustedLocalCertificates);
        report.call("Check of OCSP responder certificate done");
        return results;
    }
}
