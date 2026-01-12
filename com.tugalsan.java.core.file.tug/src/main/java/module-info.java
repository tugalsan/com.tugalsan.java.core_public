module com.tugalsan.java.core.file.tug {
    requires java.desktop;
    requires jai.imageio.core;
//    requires net.sf.cssbox.pdf2dom;
    requires com.tugalsan.java.core.crypto;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.file.common;
    requires com.tugalsan.java.core.url;
    
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.shape;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.file.html;
    requires com.tugalsan.java.core.file.img;
    exports com.tugalsan.java.core.file.tug.server;
}
