module com.tugalsan.java.core.file.pdf.pdfbox3.pdf2dom {
    requires java.desktop;
    requires org.apache.commons.io;
    requires org.apache.fontbox;
    requires org.apache.pdfbox;
    requires org.apache.pdfbox.io;
    requires org.slf4j;
    requires com.tugalsan.java.core.file.pdf.pdfbox3.pdf2dom.fontverter;
    requires com.tugalsan.java.core.file.pdf.pdfbox3.pdf2dom.gfxassert;
    requires com.tugalsan.java.core.function;
    exports org.fit.pdfdom;
//    exports org.fit.pdfdom.resource;
}
