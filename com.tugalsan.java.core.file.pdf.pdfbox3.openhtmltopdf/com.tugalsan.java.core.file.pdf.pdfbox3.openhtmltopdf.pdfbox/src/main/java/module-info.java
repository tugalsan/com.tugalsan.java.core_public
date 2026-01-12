module com.tugalsan.java.core.file.pdf.pdfbox3.openhtmltopdf.pdfbox {
    requires java.desktop;
    requires java.logging;
    requires org.apache.pdfbox;
    requires org.apache.fontbox;
    requires org.apache.xmpbox;
    requires de.rototor.pdfbox.graphics2d;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.file.pdf.pdfbox3.openhtmltopdf.core;
    exports com.openhtmltopdf.pdfboxout;
}
