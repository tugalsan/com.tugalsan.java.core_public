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
import module com.tugalsan.java.core.file.pdf.pdfbox3;
import module com.tugalsan.java.core.string;
import module com.tugalsan.java.core.time;
import module com.tugalsan.java.core.function;
import module org.apache.pdfbox;
import module org.apache.pdfbox.io;
import module org.bouncycastle.provider;
import module org.bouncycastle.pkix;
import org.bouncycastle.asn1.x509.Time;
import org.bouncycastle.util.Store;
import org.apache.pdfbox.util.Hex;
import org.apache.pdfbox.io.IOUtils;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.text.*;
import java.util.List;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;

/**
 * This will get the signature(s) from the document, do some verifications and
 * show the signature(s) and the certificates. This is a complex topic - the
 * code here is an example and not a production-ready solution.
 *
 * @author Ben Litchfield
 */
public final class ShowSignatureModified {

    private ShowSignatureModified() {

    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static TS_FilePdfBox3UtilsSign.VerityResult prepareReport(TGS_FuncMTU_In1<String> _report, String optional_passwordIfPdfEncoded, Path pdfFile, List<Certificate> trustedLocalCertificates) throws IOException, GeneralSecurityException, TSPException, CertificateVerificationException {

//        showSignature(optional_passwordIfPdfEncoded, pdfFile);
//    }
        /**
         * This is the entry point for the application.
         *
         * @param args The command-line arguments.
         *
         * @throws IOException If there is an error reading the file.
         * @throws org.bouncycastle.tsp.TSPException
         * @throws
         * org.apache.pdfbox.examples.signature.cert.CertificateVerificationException
         * @throws java.security.GeneralSecurityException
         */
//    public static void main(String[] args) throws IOException,
//            TSPException,
//            CertificateVerificationException,
//            GeneralSecurityException {
//        // register BouncyCastle provider, needed for "exotic" algorithms
//        Security.addProvider(SecurityProvider.getProvider());
//
//        ShowSignatureModified show = new ShowSignatureModified();
//        show.showSignature(args);
//    }
//    private void showSignature(String optional_passwordIfPdfEncoded, Path pdfFile) throws IOException,
//            GeneralSecurityException,
//            TSPException,
//            CertificateVerificationException {
        var result = new TS_FilePdfBox3UtilsSign.VerityResult();
        TGS_FuncMTU_In1<String> report = log -> {
            _report.call(log);
            result.report_call(log);
        };
        return TGS_FuncMTCUtils.call(() -> {
            //WARMUP
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }

            var password = optional_passwordIfPdfEncoded == null ? "" : optional_passwordIfPdfEncoded;
            result.password = password;
            var infile = pdfFile.toFile();
            result.pdfFile = pdfFile;
            // use old-style document loading to disable leniency
            // see also https://www.pdf-insecurity.org/
            var raFile = new RandomAccessReadBufferedFile(infile);
            // If your files are not too large, you can also download the PDF into a byte array
            // with IOUtils.toByteArray() and pass a RandomAccessBuffer() object to the
            // PDFParser constructor.
            var parser = new PDFParser(raFile, password);
            try (var document = parser.parse(false)) {
                for (var sig : document.getSignatureDictionaries()) {
                    var signature = result.newSignature();
                    var sigStream = sig.getCOSObject();
                    var contents = sig.getContents();

                    // download the signed content
                    // we're doing this as a stream, to be able to handle huge files                    
                    try (var fis = new FileInputStream(infile); var signedContentAsStream = new COSFilterInputStream(fis, sig.getByteRange())) {
                        report.call("Signature found");

                        if (sig.getName() != null) {
                            report.call("Name:     " + sig.getName());
                            signature.name = sig.getName();
                        }
                        if (sig.getLocation() != null) {
                            report.call("Location:     " + sig.getLocation());
                            signature.location = sig.getLocation();
                        }
                        if (sig.getReason() != null) {
                            report.call("Reason:     " + sig.getReason());
                            signature.reason = sig.getReason();
                        }
                        if (sig.getSignDate() != null) {
                            report.call("Modified: " + sdf.format(sig.getSignDate().getTime()));
                            signature.dateModified = TGS_Time.of(sig.getSignDate().getTime());
                        }
                        var subFilter = sig.getSubFilter();
                        if (subFilter != null) {
                            signature.subFilter = subFilter;
                            switch (subFilter) {
                                case "adbe.pkcs7.detached", "ETSI.CAdES.detached" -> {
                                    report.call("case \"adbe.pkcs7.detached\" OR case \"ETSI.CAdES.detached\"");
                                    signature.trustAnchorStatus = verifyPKCS7(report, signedContentAsStream, contents, sig, trustedLocalCertificates);
                                }
                                case "adbe.pkcs7.sha1" -> {
                                    report.call("case \"adbe.pkcs7.sha1\"");
                                    // example: PDFBOX-1452.pdf
                                    CertificateFactory factory = CertificateFactory.getInstance("X.509");
                                    ByteArrayInputStream certStream = new ByteArrayInputStream(contents);
                                    Collection<? extends Certificate> certs = factory.generateCertificates(certStream);
                                    report.call("certs=" + certs);
                                    @SuppressWarnings({"squid:S5542", "lgtm [java/weak-cryptographic-algorithm]"})
                                    MessageDigest md = MessageDigest.getInstance("SHA1");
                                    try (DigestInputStream dis = new DigestInputStream(signedContentAsStream, md)) {
                                        while (dis.read() != -1) {
                                            // do nothing
                                        }
                                    }
                                    byte[] hash = md.digest();
                                    signature.trustAnchorStatus = verifyPKCS7(report, new ByteArrayInputStream(hash), contents, sig, trustedLocalCertificates);
                                }
                                case "adbe.x509.rsa_sha1" -> {
                                    Boolean timeStampSignatureVerified = null;
                                    Boolean certificateValidAtSigningTime = null;
                                    Boolean signatureVerified = null;
                                    Boolean isSelfSigned = null;

                                    report.call("case \"adbe.x509.rsa_sha1\"");
                                    // example: PDFBOX-2693.pdf
                                    var certString = (COSString) sigStream.getDictionaryObject(COSName.CERT);
                                    //TODO this could also be an array.
                                    if (certString == null) {
                                        report.call("case \"adbe.x509.rsa_sha1\"");
                                        report.call("The /Cert certificate string is missing in the signature dictionary");
                                        return result;
                                    }
                                    var certData = certString.getBytes();
                                    var factory = CertificateFactory.getInstance("X.509");
                                    var certStream = new ByteArrayInputStream(certData);
                                    Collection<? extends Certificate> certs = factory.generateCertificates(certStream);
                                    report.call("certs=" + certs);
                                    X509Certificate cert = (X509Certificate) certs.iterator().next();
                                    // to verify signature, see code at
                                    // https://stackoverflow.com/questions/43383859/
                                    try {
                                        if (sig.getSignDate() != null) {
                                            cert.checkValidity(sig.getSignDate().getTime());
                                            report.call("Certificate valid at signing time");
                                            certificateValidAtSigningTime = true;
                                        } else {
                                            report.call("Certificate cannot be verified without signing time");
                                            certificateValidAtSigningTime = null;
                                        }
                                    } catch (CertificateExpiredException ex) {
                                        report.call("Certificate expired at signing time");
                                        certificateValidAtSigningTime = false;
                                    } catch (CertificateNotYetValidException ex) {
                                        report.call("Certificate not yet valid at signing time");
                                        certificateValidAtSigningTime = false;
                                    }
                                    if (CertificateVerifierModified.isSelfSigned(report, cert)) {
                                        report.call("Certificate is self-signed, LOL!");
                                        isSelfSigned = true;
                                        IO.println("RETURNING_SELF_SIGN RESULT TO isSelfSigned @ isSelfSigned(report, additionalCert) @ adbe.x509.rsa_sha1 @ preparereport @ ShowSignatureModified");
                                    } else {
                                        report.call("Certificate is not self-signed");

                                        if (sig.getSignDate() != null) {
                                            @SuppressWarnings("unchecked")
                                            Store<X509CertificateHolder> store = new JcaCertStore(certs);
                                            var vcc = SigUtilsModified.verifyCertificateChain(report, store, cert, sig.getSignDate().getTime(), trustedLocalCertificates);
                                            signature.trustAnchorStatus = TS_FilePdfBox3UtilsSign.TrustAnchorStatus.getInstace(
                                                    vcc, timeStampSignatureVerified, certificateValidAtSigningTime, signatureVerified
                                            );
                                            if (isSelfSigned != null && isSelfSigned) {
                                                IO.println("RETURNING_SELF_SIGN RESULT TO isSelfSigned @ adbe.x509.rsa_sha1");
                                                signature.trustAnchorStatus = signature.trustAnchorStatus.getInstace_selfSigned();
                                            }
                                        }
                                    }
                                }
                                case "ETSI.RFC3161" -> {
                                    report.call("case \"ETSI.RFC3161\"");
                                    // e.g. PDFBOX-1848, file_timestamped.pdf
                                    signature.trustAnchorStatus = verifyETSIdotRFC3161(report, signedContentAsStream, contents, trustedLocalCertificates);
                                    // verifyPKCS7(hash, contents, sig) does not work
                                }
                                default -> {
                                    report.call("default");
                                    report.call("Unknown certificate type: " + subFilter);
                                }
                            }
                        } else {
                            throw new IOException("Missing subfilter for cert dictionary");
                        }

                        var byteRange = sig.getByteRange();
                        if (byteRange.length != 4) {
                            throw new IOException("ERROR: Signature byteRange must have 4 items");
                        } else {
                            var fileLen = infile.length();
                            long rangeMax = byteRange[2] + (long) byteRange[3];
                            // multiply content length with 2 (because it is in hex in the PDF) and add 2 for < and >
                            var contentLen = contents.length * 2 + 2;
                            if (fileLen != rangeMax || byteRange[0] != 0 || byteRange[1] + contentLen != byteRange[2]) {
                                // a false result doesn't necessarily mean that the PDF is a fake
                                // see this answer why:
                                // https://stackoverflow.com/a/48185913/535646
                                report.call("Signature does not cover whole document");
                                signature.signature_covers_whole_document = false;
                            } else {
                                report.call("Signature covers whole document");
                                signature.signature_covers_whole_document = true;
                            }
                            checkContentValueWithFile(signature.manupilationFindings, report, infile, byteRange, contents);
                        }
                    }
                }
                result.report_stage_set_dds();
                analyseDSS(report, document);
            } catch (CMSException | OperatorCreationException e) {
                throw new IOException(e);
            }
            result.report_stage_set_main();
            report.call("Analyzed: " + pdfFile);
            return result;
        }, e -> {
            result.exception = e;
            return result;
        });
    }

    private static void checkContentValueWithFile(List<String> manupilationFindings, TGS_FuncMTU_In1<String> _report, File file, int[] byteRange, byte[] contents) throws IOException {
        // https://stackoverflow.com/questions/55049270
        // comment by mkl: check whether gap contains a hex value equal
        // byte-by-byte to the Content value, to prevent attacker from using a literal string
        // to allow extra space
        TGS_FuncMTU_In1<String> report = log -> {
            _report.call(log);
            manupilationFindings.add(log);
        };
        try (var raf = new RandomAccessReadBufferedFile(file)) {
            raf.seek(byteRange[1]);
            var c = raf.read();
            if (c != '<') {
                report.call("'<' expected at offset " + byteRange[1] + ", but got " + (char) c);
            }
            var contentFromFile = new byte[byteRange[2] - byteRange[1] - 2];
            var contentLength = contentFromFile.length;
            var contentBytesRead = raf.read(contentFromFile);
            while (contentBytesRead > -1 && contentBytesRead < contentLength) {
                contentBytesRead += raf.read(contentFromFile,
                        contentBytesRead,
                        contentLength - contentBytesRead);
            }
            var contentAsHex = Hex.getString(contents).getBytes(StandardCharsets.US_ASCII);
            if (contentBytesRead != contentAsHex.length) {
                report.call("Raw content length from file is "
                        + contentBytesRead
                        + ", but internal content string in hex has length "
                        + contentAsHex.length);
            }
            // Compare the two, we can't do byte comparison because of upper/lower case
            // also check that it is really hex
            for (var i = 0; i < contentBytesRead; ++i) {
                try {
                    if (Integer.parseInt(String.valueOf((char) contentFromFile[i]), 16)
                            != Integer.parseInt(String.valueOf((char) contentAsHex[i]), 16)) {
                        report.call("Possible manipulation at file offset "
                                + (byteRange[1] + i + 1) + " in signature content");
                        break;
                    }
                } catch (NumberFormatException ex) {
                    report.call("Incorrect hex value");
                    report.call("Possible manipulation at file offset "
                            + (byteRange[1] + i + 1) + " in signature content");
                    break;
                }
            }
            c = raf.read();
            if (c != '>') {
                report.call("'>' expected at offset " + byteRange[2] + ", but got " + (char) c);
            }
        }
    }

    /**
     * Verify ETSI.RFC3161 TimeStampToken
     *
     * @param signedContentAsStream the byte sequence that has been signed
     * @param contents the /Contents field as a COSString
     * @throws CMSException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws TSPException
     * @throws OperatorCreationException
     * @throws CertificateVerificationException
     * @throws CertificateException
     */
    private static TS_FilePdfBox3UtilsSign.TrustAnchorStatus verifyETSIdotRFC3161(TGS_FuncMTU_In1<String> report, InputStream signedContentAsStream, byte[] contents, List<Certificate> trustedLocalCertificates) throws CMSException, NoSuchAlgorithmException, IOException, TSPException, OperatorCreationException, CertificateVerificationException, CertificateException {
        Boolean timeStampSignatureVerified = null;
        Boolean certificateValidAtSigningTime = null;
        Boolean signatureVerified = null;
        Boolean isSelfSigned = null;

        var timeStampToken = new TimeStampToken(new CMSSignedData(contents));
        var timeStampInfo = timeStampToken.getTimeStampInfo();
        report.call("Time stamp gen time: " + timeStampInfo.getGenTime());
        if (timeStampInfo.getTsa() != null) {
            report.call("Time stamp tsa name: " + timeStampInfo.getTsa().getName());
        }

        var factory = CertificateFactory.getInstance("X.509");
        var certStream = new ByteArrayInputStream(contents);
        Collection<? extends Certificate> certs = factory.generateCertificates(certStream);
        report.call("certs=" + certs);

        var hashAlgorithm = timeStampInfo.getMessageImprintAlgOID().getId();
        // compare the hash of the signed content with the hash in the timestamp
        var md = MessageDigest.getInstance(hashAlgorithm);
        try (DigestInputStream dis = new DigestInputStream(signedContentAsStream, md)) {
            while (dis.read() != -1) {
                // do nothing
            }
        }
        if (Arrays.equals(md.digest(),
                timeStampInfo.getMessageImprintDigest())) {
            report.call("ETSI.RFC3161 timestamp signature verified");
            timeStampSignatureVerified = true;
        } else {
            report.call("ETSI.RFC3161 timestamp signature verification failed");
            timeStampSignatureVerified = false;
        }

        var certFromTimeStamp = (X509Certificate) certs.iterator().next();
        SigUtilsModified.checkTimeStampCertificateUsage(report, certFromTimeStamp);
        SigUtilsModified.validateTimestampToken(timeStampToken);
        var results = SigUtilsModified.verifyCertificateChain(report, timeStampToken.getCertificates(),
                certFromTimeStamp,
                timeStampInfo.getGenTime(), trustedLocalCertificates);
        return TS_FilePdfBox3UtilsSign.TrustAnchorStatus.getInstace(results, timeStampSignatureVerified, certificateValidAtSigningTime, signatureVerified);
    }

    /**
     * Verify a PKCS7 signature.
     *
     * @param signedContentAsStream the byte sequence that has been signed
     * @param contents the /Contents field as a COSString
     * @param sig the PDF signature (the /V dictionary)
     * @throws CMSException
     * @throws OperatorCreationException
     * @throws GeneralSecurityException
     * @throws CertificateVerificationException
     */
    private static TS_FilePdfBox3UtilsSign.TrustAnchorStatus verifyPKCS7(TGS_FuncMTU_In1<String> report, InputStream signedContentAsStream, byte[] contents, PDSignature sig, List<Certificate> trustedLocalCertificates)
            throws CMSException, OperatorCreationException,
            CertificateVerificationException, GeneralSecurityException,
            TSPException, IOException {
        List<CertificateVerifierModified.PKIXCertPathBuilderResultWithTrustAnchorsStatus> vcc = new ArrayList();
        Boolean timeStampSignatureVerified = null;
        Boolean certificateValidAtSigningTime = null;
        Boolean signatureVerified = null;
        Boolean isSelfSigned = null;
        // inspiration:
        // http://stackoverflow.com/a/26702631/535646
        // http://stackoverflow.com/a/9261365/535646
        var signedContent = new CMSProcessableInputStreamModified(signedContentAsStream);
        var signedData = new CMSSignedData(signedContent, contents);
        Store<X509CertificateHolder> certificatesStore = signedData.getCertificates();
        if (certificatesStore.getMatches(null).isEmpty()) {
            throw new IOException("No certificates in signature");
        }
        Collection<SignerInformation> signers = signedData.getSignerInfos().getSigners();
        if (signers.isEmpty()) {
            throw new IOException("No signers in signature");
        }
        var signerInformation = signers.iterator().next();
        @SuppressWarnings("unchecked")
        Collection<X509CertificateHolder> matches
                = certificatesStore.getMatches(signerInformation.getSID());
        if (matches.isEmpty()) {
            throw new IOException("Signer '" + signerInformation.getSID().getIssuer()
                    + ", serial# " + signerInformation.getSID().getSerialNumber()
                    + " does not match any certificates");
        }
        var certificateHolder = matches.iterator().next();
        report.call("certFromSignedData:");
        var certFromSignedData = new JcaX509CertificateConverter().getCertificate(certificateHolder);
        TGS_StringUtils.jre().toList_ln(certFromSignedData.toString()).forEach(line -> {
            report.call(" - " + line);
        });

        SigUtilsModified.checkCertificateUsage(report, certFromSignedData);

        // Embedded timestamp
        var timeStampToken = SigUtilsModified.extractTimeStampTokenFromSignerInformation(signerInformation);
        if (timeStampToken != null) {
            // tested with QV_RCA1_RCA3_CPCPS_V4_11.pdf
            // https://www.quovadisglobal.com/~/media/Files/Repository/QV_RCA1_RCA3_CPCPS_V4_11.ashx
            // also 021496.pdf and 036351.pdf from digitalcorpora
            SigUtilsModified.validateTimestampToken(timeStampToken);
            var certFromTimeStamp = SigUtilsModified.getCertificateFromTimeStampToken(timeStampToken);
            // merge both stores using a set to remove duplicates
            HashSet<X509CertificateHolder> certificateHolderSet = new HashSet<>();
            certificateHolderSet.addAll(certificatesStore.getMatches(null));
            certificateHolderSet.addAll(timeStampToken.getCertificates().getMatches(null));
            var vcc_chain = SigUtilsModified.verifyCertificateChain(report, new CollectionStore<>(certificateHolderSet),
                    certFromTimeStamp,
                    timeStampToken.getTimeStampInfo().getGenTime(), trustedLocalCertificates);
            vcc.addAll(vcc_chain);

            SigUtilsModified.checkTimeStampCertificateUsage(report, certFromTimeStamp);

            // compare the hash of the signature with the hash in the timestamp
            var tsMessageImprintDigest = timeStampToken.getTimeStampInfo().getMessageImprintDigest();
            var hashAlgorithm = timeStampToken.getTimeStampInfo().getMessageImprintAlgOID().getId();
            var sigMessageImprintDigest = MessageDigest.getInstance(hashAlgorithm).digest(signerInformation.getSignature());
            if (Arrays.equals(tsMessageImprintDigest, sigMessageImprintDigest)) {
                report.call("timestamp signature verified");
                timeStampSignatureVerified = true;
            } else {
                report.call("timestamp signature verification failed");
                timeStampSignatureVerified = false;
            }
        }

        try {
            if (sig.getSignDate() != null) {
                certFromSignedData.checkValidity(sig.getSignDate().getTime());
                report.call("Certificate valid at signing time");
                certificateValidAtSigningTime = true;
            } else {
                report.call("Certificate cannot be verified without signing time");
                certificateValidAtSigningTime = null;
            }
        } catch (CertificateExpiredException ex) {
            report.call("Certificate expired at signing time");
            certificateValidAtSigningTime = false;
        } catch (CertificateNotYetValidException ex) {
            report.call("Certificate not yet valid at signing time");
            certificateValidAtSigningTime = false;
        }

        // usually not available
        if (signerInformation.getSignedAttributes() != null) {
            // From SignedMailValidator.getSignatureTime()
            var signingTime = signerInformation.getSignedAttributes().get(CMSAttributes.signingTime);
            if (signingTime != null) {
                var timeInstance = Time.getInstance(signingTime.getAttrValues().getObjectAt(0));
                try {
                    certFromSignedData.checkValidity(timeInstance.getDate());
                    report.call("Certificate valid at signing time: " + timeInstance.getDate());
                } catch (CertificateExpiredException ex) {
                    report.call("Certificate expired at signing time");
                    if (certificateValidAtSigningTime) {
                        certificateValidAtSigningTime = false;
                    }
                } catch (CertificateNotYetValidException ex) {
                    report.call("Certificate not yet valid at signing time");
                    if (certificateValidAtSigningTime) {
                        certificateValidAtSigningTime = false;
                    }
                }
            }
        }

        if (signerInformation.verify(new JcaSimpleSignerInfoVerifierBuilder().
                setProvider(SecurityProvider.getProvider()).build(certFromSignedData))) {
            report.call("Signature verified");
            signatureVerified = true;
        } else {
            report.call("Signature verification failed");
            signatureVerified = false;
        }

        if (CertificateVerifierModified.isSelfSigned(report, certFromSignedData)) {
            report.call("Certificate is self-signed, LOL!");
            vcc.add(new CertificateVerifierModified.PKIXCertPathBuilderResultWithTrustAnchorsStatus(null, false, true, false, false, false));
            isSelfSigned = true;
            IO.println("RETURNING_SELF_SIGN RESULT TO isSelfSigned @ CertificateVerifier.isSelfSigned(certFromSignedData) @ CertificateVerifier.isSelfSigned(certFromSignedData)  @ ShowSignatureModified");
        } else {
            report.call("Certificate is not self-signed");
            if (sig.getSignDate() != null) {
                var vcc_chain = SigUtilsModified.verifyCertificateChain(report, certificatesStore, certFromSignedData, sig.getSignDate().getTime(), trustedLocalCertificates);
                vcc.addAll(vcc_chain);
            } else {
                report.call("Certificate cannot be verified without signing time");
            }
        }
        var trustAnchorStatus = TS_FilePdfBox3UtilsSign.TrustAnchorStatus.getInstace(vcc,
                timeStampSignatureVerified, certificateValidAtSigningTime, signatureVerified
        );
        if (isSelfSigned != null && isSelfSigned) {
            IO.println(">>> CHANGING RESULT TO isSelfSigned @ verifyPKCS7");
            return trustAnchorStatus.getInstace_selfSigned();
        }
        return trustAnchorStatus;
    }

    // for later use: get all root certificates. Will be used to check
    // whether we trust the root in the certificate chain.
    private static Set<X509Certificate> getRootCertificates()
            throws GeneralSecurityException, IOException {
        Set<X509Certificate> rootCertificates = new HashSet<>();

        // https://stackoverflow.com/questions/3508050/
        var filename = System.getProperty("java.home") + "/lib/security/cacerts";
        KeyStore keystore;
        try (FileInputStream is = new FileInputStream(filename)) {
            keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystore.load(is, null);
        }
        PKIXParameters params = new PKIXParameters(keystore);
        for (TrustAnchor trustAnchor : params.getTrustAnchors()) {
            rootCertificates.add(trustAnchor.getTrustedCert());
        }

        // https://www.oracle.com/technetwork/articles/javase/security-137537.html
        try {
            keystore = KeyStore.getInstance("Windows-ROOT");
            keystore.load(null, null);
            params = new PKIXParameters(keystore);
            for (TrustAnchor trustAnchor : params.getTrustAnchors()) {
                rootCertificates.add(trustAnchor.getTrustedCert());
            }
        } catch (InvalidAlgorithmParameterException | KeyStoreException ex) {
            // empty or not windows
        }

        return rootCertificates;
    }

    /**
     * Analyzes the DSS-Dictionary (Document Security Store) of the document.
     * Which is used for signature validation. The DSS is defined in PAdES Part
     * 4 - Long Term Validation.
     *
     * @param document PDDocument, to get the DSS from
     */
    private static void analyseDSS(TGS_FuncMTU_In1<String> report, PDDocument document) throws IOException {
        PDDocumentCatalog catalog = document.getDocumentCatalog();
        COSBase dssElement = catalog.getCOSObject().getDictionaryObject(COSName.DSS);

        if (dssElement instanceof COSDictionary dss) {
            report.call("DSS Dictionary: " + dss);
            var certsElement = dss.getDictionaryObject(COSName.CERTS);
            if (certsElement instanceof COSArray cOSArray) {
                printStreamsFromArray(report, cOSArray, "Cert");
            }
            var ocspsElement = dss.getDictionaryObject(COSName.OCSPS);
            if (ocspsElement instanceof COSArray cOSArray) {
                printStreamsFromArray(report, cOSArray, "Ocsp");
            }
            var crlElement = dss.getDictionaryObject(COSName.CRLS);
            if (crlElement instanceof COSArray cOSArray) {
                printStreamsFromArray(report, cOSArray, "CRL");
            }
            // TODO: go through VRIs (which indirectly point to the DSS-Data)
        }
    }

    /**
     * Go through the elements of a COSArray containing each an COSStream to
     * print in Hex.
     *
     * @param elements COSArray of elements containing a COS Stream
     * @param description to append on Print
     * @throws IOException
     */
    private static void printStreamsFromArray(TGS_FuncMTU_In1<String> report, COSArray elements, String description) throws IOException {
        for (var baseElem : elements) {
            var streamObj = (COSObject) baseElem;
            if (streamObj.getObject() instanceof COSStream cosStream) {
                try (var is = cosStream.createInputStream()) {
                    var streamBytes = IOUtils.toByteArray(is);
                    report.call(description + " (" + elements.indexOf(streamObj) + "): "
                            + Hex.getString(streamBytes));
                }
            }
        }
    }

    /**
     * This will print a usage message.
     */
//    private static void usage() {
//        report.call("usage: java " + ShowSignatureModified.class.getName()
//                + " <password (usually empty)> <inputfile>");
//        // The password is for encrypted files and has nothing to do with the signature.
//        // (A PDF can be both encrypted and signed)
//    }
}
