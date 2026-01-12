module com.tugalsan.java.core.file.pdf.openpdf {
    requires java.desktop;
    requires org.jsoup;
    requires flying.saucer;
    requires flying.saucer.pdf;
    requires org.apache.commons.io;
    requires com.github.librepdf.openpdf;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.file.common;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.font;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.shape;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.file.img;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.string;
    exports com.tugalsan.java.core.file.pdf.openpdf.server;
}
