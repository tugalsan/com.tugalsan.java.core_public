module com.tugalsan.java.core.ocr.tesseract {
    requires java.desktop;
    requires tess4j;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.function;
    exports com.tugalsan.java.core.ocr.tesseract.server;
}
