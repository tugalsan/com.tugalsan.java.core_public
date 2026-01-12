module com.tugalsan.java.core.file.html {
    requires gwt.user;
    requires java.desktop;
    requires org.jsoup;
    requires org.apache.commons.text;    
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.crypto;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.network;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.file.common;
    exports com.tugalsan.java.core.file.html.client;
    exports com.tugalsan.java.core.file.html.client.element;
    exports com.tugalsan.java.core.file.html.server;
    exports com.tugalsan.java.core.file.html.server.element;
}
