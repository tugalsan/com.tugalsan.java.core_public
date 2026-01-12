package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.file.html;
import module com.tugalsan.java.core.file;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.network;
import module com.tugalsan.java.core.time;
import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.url;
import module java.desktop;
import module org.apache.pdfbox;//import module pdfbox.examples;
import module org.bouncycastle.provider;
import com.tugalsan.java.core.file.pdf.pdfbox3.server.sign.CertificateVerifierModified;
import com.tugalsan.java.core.file.pdf.pdfbox3.server.sign.CreateSignatureBaseModified;
import com.tugalsan.java.core.file.pdf.pdfbox3.server.sign.CreateVisibleSignature2Modified;
import com.tugalsan.java.core.file.pdf.pdfbox3.server.sign.ShowSignatureModified;
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.cert.Certificate;
import java.time.*;
import java.util.*;
import java.util.List;
import java.util.stream.*;
import org.apache.pdfbox.Loader;

public class TS_FilePdfBox3UtilsSign {

    final private static TS_Log d = TS_Log.of(TS_FilePdfBox3UtilsSign.class);

    public static void verify_preOperations_downloadTrustedCertificatesToDir(Path dirSSL) {
        var trusted = dirSSL.resolve("trusted");
        TS_DirectoryUtils.createDirectoriesIfNotExists(trusted);
        d.cr("verify_preOperations_downloadTrustedCertificatesToDir", "trusted", trusted);
        var downloadTimeout = Duration.ofSeconds(60);
        var urlsCertAll = TS_FileHtmlUtils.parseLinks_usingRegex(
                List.of(
                        TGS_Url.of("https://letsencrypt.org/certificates/"),
                        TGS_Url.of("https://www.freetsa.org/index_en.php")
                ),
                downloadTimeout, true, true,
                u -> {
                    return TGS_CharSetCast.current().endsWithIgnoreCase(u.toString(), "der")
                    || TGS_CharSetCast.current().endsWithIgnoreCase(u.toString(), "pem")
                    || TGS_CharSetCast.current().endsWithIgnoreCase(u.toString(), "crt");
                }
        );
        d.cr("verify_preOperations_downloadTrustedCertificatesToDir", "urlsCertAll", "size", urlsCertAll.size());
        d.cr("verify_preOperations_downloadTrustedCertificatesToDir", trusted, "fileCount.pre", TS_DirectoryUtils.subFiles(trusted, null, false, false).size());
        TS_UrlDownloadUtils.toFolder(urlsCertAll, trusted, downloadTimeout);
        d.cr("verify_preOperations_downloadTrustedCertificatesToDir", trusted, "fileCount.pst", TS_DirectoryUtils.subFiles(trusted, null, false, false).size());
    }

    public static List<Certificate> verify_preOperations_fetchTrustedCertificatesFromDir(Path dirSSL, CharSequence p12Pass) {
        return TS_NetworkSSLUtils.toCertificatesFromDirectory(dirSSL, p12Pass);
    }

    public static class VerityResult {

        public Exception exception;
        public String password;
        public Path pdfFile;
        final public List<VerifySignature> signatures = new ArrayList();
        final public VerityDDS dds = new VerityDDS();
        final public List<String> report = new ArrayList();

        public String toString(String suffix) {
            var sj = new StringJoiner(suffix);

            addHeader.run(sj, VerityResult.class.getSimpleName() + " STAGE 1.1 - MAIN");
            sj.add("PARAMS");
            sj.add("password: " + password);
            sj.add("pdfFile: " + pdfFile.toString());
            sj.add("");
            sj.add("REPORT");
            if (report.isEmpty()) {
                sj.add("report.isEmpty()");
            } else {
                sj.add("report.size(): " + report.size());
                report.forEach(s -> sj.add(s));
            }
            sj.add("");
            sj.add("EXCEPTION");
            if (exception == null) {
                sj.add("exception == null");
            } else {
                sj.add(exception.getMessage());
                Arrays.stream(exception.getStackTrace()).forEach(log -> sj.add(String.valueOf(log)));
            }

            addHeader.run(sj, VerityResult.class.getSimpleName() + " STAGE 2 - SIGNATURES");
            if (signatures.isEmpty()) {
                sj.add("signatures.isEmpty()");
            } else {
                sj.add("signatures.size(): " + signatures.size());

                IntStream.range(0, signatures.size()).forEachOrdered(i -> {
                    var sig = signatures.get(i);
                    addHeader.run(sj, VerityResult.class.getSimpleName() + " STAGE 2 [" + (i + 1) + "/" + signatures.size() + "] - SIGNATURE");
                    sj.add("SIGNATURE PARAMS");
                    sj.add("sig.name: " + sig.name);
                    sj.add("sig.location: " + sig.location);
                    sj.add("sig.reason: " + sig.reason);
                    sj.add("sig.dateModified: " + sig.dateModified.toString_YYYY_MM_DD_HH_MM_SS());
                    sj.add("sig.subFilter: " + sig.subFilter);
                    sj.add("sig.signature_covers_whole_document: " + sig.signature_covers_whole_document);
                    sj.add("");

                    sj.add("SIGNATURE TRUST ANCHOR STATUS");
                    if (sig.trustAnchorStatus == null) {
                        sj.add("sig.trustAnchorStatus.isEmpty()");
                    } else {
                        sj.add("sig.trustAnchorStatus.isSelfSigned: " + sig.trustAnchorStatus.isSelfSigned());
                        sj.add("sig.trustAnchorStatus.isContainsLocal: " + sig.trustAnchorStatus.isContainsLocal());
                        if (sig.trustAnchorStatus.isFreeTSA()) {
                            sj.add("sig.trustAnchorStatus.isFreeTSA: yes_manually_added_guessed_because_guessed_that_it_is_valid");
                        }
                        if (sig.trustAnchorStatus.isISRG()) {
                            sj.add("sig.trustAnchorStatus.isISRG: yes_manually_added_guessed_because_guessed_that_it_is_valid");
                        }
                        if (sig.trustAnchorStatus.isLetsEncrypt()) {
                            sj.add("sig.trustAnchorStatus.isLetsEncrypt: yes_manually_added_guessed_because_guessed_that_it_is_valid");
                        }
                        sj.add("sig.trustAnchorStatus.timeStampSignatureVerified: " + sig.trustAnchorStatus.timeStampSignatureVerified());
                        sj.add("sig.trustAnchorStatus.certificateValidAtSigningTime: " + sig.trustAnchorStatus.certificateValidAtSigningTime());
                        sj.add("sig.trustAnchorStatus.signatureVerified: " + sig.trustAnchorStatus.signatureVerified());
                    }

                    sj.add("");

                    sj.add("SIGNATURE MANUPULATION FINDINGS");
                    if (sig.manupilationFindings.isEmpty()) {
                        sj.add("sig.manupilationFindings.isEmpty()");
                    } else {
                        sj.add("sig.simanupilationFindings.size(): " + sig.manupilationFindings.size());
                        sig.manupilationFindings.forEach(s -> sj.add(s));
                    }

                    sj.add("");

                    sj.add("SIGNATURE REPORT");
                    if (sig.report.isEmpty()) {
                        sj.add("sig.report.isEmpty()");
                    } else {
                        sj.add("sig.report.size(): " + sig.report.size());
                        sig.report.forEach(s -> sj.add(s));
                    }
                });
            }

            addHeader.run(sj, VerityResult.class.getSimpleName() + " STAGE 3 - DDS");
            sj.add("DDS_CERTS");
            if (dds.DDS_CERTS.isEmpty()) {
                sj.add("dds.DDS_CERTS.isEmpty()");
            } else {
                dds.DDS_CERTS.forEach(m -> sj.add(m));
            }
            sj.add("");
            sj.add("DDS_OCSPS");
            if (dds.DDS_OCSPS.isEmpty()) {
                sj.add("dds.DDS_OCSPS.isEmpty()");
            } else {
                dds.DDS_OCSPS.forEach(m -> sj.add(m));
            }
            sj.add("");
            sj.add("DDS_CRLS");
            if (dds.DDS_CRLS.isEmpty()) {
                sj.add("dds.DDS_CRLS.isEmpty()");
            } else {
                dds.DDS_CRLS.forEach(m -> sj.add(m));
            }
            sj.add("");
            sj.add("DDS_VRI");
            if (dds.DDS_VRI.isEmpty()) {
                sj.add("dds.DDS_VRI.isEmpty() or not implemented");
            } else {
                dds.DDS_VRI.forEach(m -> sj.add(m));
            }
            sj.add("");
            sj.add("DDS_REPORT");
            if (dds.report.isEmpty()) {
                sj.add("dds.report.isEmpty()");
            } else {
                dds.report.forEach(m -> sj.add(m));
            }

            return sj.toString();
        }

        @Override
        public String toString() {
            return toString("\n");
        }
        final private static TGS_FuncMTU_In1<StringJoiner> addLineBreak = sj -> {
            sj.add("------------------------------------------------------------------------------");
        };
        final private static TGS_FuncMTU_In2<StringJoiner, String> addHeader = (sj, headerText) -> {
            sj.add("");
            sj.add("");
            addLineBreak.run(sj);
            sj.add("### " + headerText + "###");
            addLineBreak.run(sj);
        };

        public VerifySignature newSignature() {
            var signature = new TS_FilePdfBox3UtilsSign.VerifySignature();
            signatures.add(signature);
            stage = signature;
            return signature;
        }
        final private VerifySignature dummyDDS = new VerifySignature();
        final private VerifySignature dummyMain = new VerifySignature();
        private VerifySignature stage = dummyMain;

        public void report_stage_set_dds() {
            stage = dummyDDS;
        }

        public void report_stage_set_main() {
            stage = dummyMain;
        }

        public void report_call(String log) {
            var foundSignature = signatures.stream().filter(s -> stage.equals(s)).findAny().orElse(null);
            if (foundSignature == null) {
                if (stage.equals(dummyDDS)) {
                    dds.report.add(log);
                }
                if (stage.equals(dummyMain)) {
                    report.add(log);
                }
            } else {
                foundSignature.report.add(log);
            }
        }
    }

    public static class VerityDDS {

        final public List<String> DDS_CERTS = new ArrayList();
        final public List<String> DDS_OCSPS = new ArrayList();
        final public List<String> DDS_CRLS = new ArrayList();
        final public List<String> DDS_VRI = new ArrayList();
        final public List<String> report = new ArrayList();
    }

    public static class VerifySignature {

        public String name;
        public String location;
        public String reason;
        public TGS_Time dateModified;
        public String subFilter;
        public boolean signature_covers_whole_document;
        public TrustAnchorStatus trustAnchorStatus;
        final public List<String> manupilationFindings = new ArrayList();
        final public List<String> report = new ArrayList();

    }

    public static record TrustAnchorStatus(Boolean isContainsLocal, Boolean isSelfSigned, Boolean isLetsEncrypt, Boolean isFreeTSA, Boolean isISRG,
            Boolean timeStampSignatureVerified, Boolean certificateValidAtSigningTime, Boolean signatureVerified) {

        public static TrustAnchorStatus getInstace(List<CertificateVerifierModified.PKIXCertPathBuilderResultWithTrustAnchorsStatus> vcc, Boolean timeStampSignatureVerified, Boolean certificateValidAtSigningTime, Boolean signatureVerified) {
            if (vcc.isEmpty()) {
                return new TrustAnchorStatus(
                        null, null, null, null, null,
                        timeStampSignatureVerified, certificateValidAtSigningTime, signatureVerified
                );
            }
            var t = new TrustAnchorStatus(
                    vcc.stream().anyMatch(r -> r.isContainsLocal()),
                    vcc.stream().anyMatch(r -> r.isSelfSigned()),
                    vcc.stream().anyMatch(r -> r.isLetsEncrypt()),
                    vcc.stream().anyMatch(r -> r.isFreeTSA()),
                    vcc.stream().anyMatch(r -> r.isISRG()),
                    timeStampSignatureVerified, certificateValidAtSigningTime, signatureVerified
            );
            if (t.isSelfSigned()) {
                IO.println("RETURNING_SELF_SIGN RESULT TO isSelfSigned @ getInstace");
            }
            return t;
        }

        public TrustAnchorStatus getInstace_selfSigned() {
            IO.println("RETURNING_SELF_SIGN RESULT TO isSelfSigned @ getInstace_selfSigned");
            return new TrustAnchorStatus(isContainsLocal,
                    true, isLetsEncrypt, isFreeTSA, isISRG,
                    timeStampSignatureVerified, certificateValidAtSigningTime, signatureVerified
            );
        }

    }

    public static TGS_UnionExcuse<VerityResult> verify(TGS_FuncMTU_In1<String> report, String optional_passwordIfPdfEncoded, Path pdfFile, List<Certificate> trustedLocalCertificates) {
        return TGS_FuncMTCUtils.call(() -> {
            return TGS_UnionExcuse.of(ShowSignatureModified.prepareReport(report, optional_passwordIfPdfEncoded, pdfFile, trustedLocalCertificates));
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    @Deprecated //NOT WORKIG (?)
    public static TGS_UnionExcuse<Boolean> isPDFSigned(Path pathPdfInput) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var doc = Loader.loadPDF(new RandomAccessReadBufferedFile(pathPdfInput.toAbsolutePath().toString()))) {
                return TGS_UnionExcuse.of(!doc.getSignatureDictionaries().isEmpty());
            }
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public TGS_UnionExcuse<Boolean> isSignatureFiledExists(Path pathPdfInput) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var doc = Loader.loadPDF(new RandomAccessReadBufferedFile(pathPdfInput.toAbsolutePath().toString()))) {
                var acroForm = doc.getDocumentCatalog().getAcroForm(null);
                if (acroForm == null) {
                    return TGS_UnionExcuse.of(false);
                }
                var signatureField = (PDSignatureField) acroForm.getField(SignConfig.SIGNATURE_FIELD_NAME);
                return TGS_UnionExcuse.of(signatureField.getSignature() != null);
            }
        }, e -> TGS_UnionExcuse.ofExcuse(e));

    }

    public static record SignConfig(
            Path pathStore, CharSequence password,
            Rectangle2D.Float optional_rectImgSign, Path optional_pathImgSign,
            boolean useExternalSignScenario,
            TGS_Url optional_urlTsa,
            String name, String place, String reason) {

        final public static String SIGNATURE_FIELD_NAME = "signatureFieldName";
        final public static TGS_Url DEFAULT_TSA_URL = TGS_Url.of("https://freetsa.org/tsr");
    }

    public static TGS_UnionExcuseVoid sign(TS_FilePdfBox3UtilsSign.SignConfig signConfig, Path pathPdfInput, Path pathPdfOutput) {
        return TGS_FuncMTCUtils.call(() -> {
            //WARMUP
            if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
                Security.addProvider(new BouncyCastleProvider());
            }

            //VARIABLES
            var pathStore = signConfig.pathStore();//Path.of("C:\\dat\\ssl\\mesa\\tomcat.jks");
            var password = signConfig.password();//"MyPass";
//            var pathPdfInput = signConfig.pathPdfInput();//Path.of("C:\\git\\blg\\com.tugalsan.blg.pdf.pdfbox3.sign\\HelloWorld.pdf");
//            var pathPdfOutput = signConfig.pathPdfOutput();//pathPdfInput.resolveSibling(pathPdfInput.toFile().getName().substring(0, pathPdfInput.toFile().getName().lastIndexOf(".")) + "_signed.pdf");
            var optional_pathImgSign = signConfig.optional_pathImgSign();//null;
            var optional_rectImgSign = signConfig.optional_rectImgSign();//new Rectangle2D.Float(10, 200, 150, 50);
            var useExternalSignScenario = signConfig.useExternalSignScenario();//false;
            var optional_urlTsa = signConfig.optional_urlTsa();//null;
            var signatureFieldName = TS_FilePdfBox3UtilsSign.SignConfig.SIGNATURE_FIELD_NAME;//SIGNATURE_FIELD_NAME;
            var name = signConfig.name();
            var place = signConfig.place();
            var reason = signConfig.reason();

            //FIX
            if (optional_rectImgSign == null) {
                optional_rectImgSign = new Rectangle2D.Float();
            }

            //KEYSTORE
            KeyStore keystore;
            var strPathStore = pathStore.toString().toUpperCase();
            if (strPathStore.endsWith("JKS")) {
                keystore = KeyStore.getInstance("JKS");
            } else {
                keystore = KeyStore.getInstance("PKCS12");
            }
            try (InputStream is = Files.newInputStream(pathStore)) {
                keystore.load(is, password.toString().toCharArray());
            }

            //SIGNER
            var signer = new CreateVisibleSignature2Modified(CreateSignatureBaseModified.SIGNATURE_ALGORITHM_SHA256WithECDSA, keystore, password.toString().toCharArray());
            if (optional_pathImgSign != null) {
                signer.setImageFile(optional_pathImgSign.toFile());
            }
            signer.setExternalSigning(useExternalSignScenario);
            signer.signPDF(
                    pathPdfInput.toFile(),
                    pathPdfOutput.toFile(),
                    optional_rectImgSign,
                    optional_urlTsa == null ? null : optional_urlTsa.toString(),
                    signatureFieldName,
                    name, place, reason
            );
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e));
    }

}
