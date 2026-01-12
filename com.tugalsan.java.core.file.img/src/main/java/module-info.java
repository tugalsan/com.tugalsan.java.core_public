module com.tugalsan.java.core.file.img {
    requires gwt.user;
    requires java.desktop;
    requires net.coobird.thumbnailator;
    requires imageio.apng;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.crypto;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.shape;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.random;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.gui;
    exports com.tugalsan.java.core.file.img.client;
    exports com.tugalsan.java.core.file.img.server;
}
