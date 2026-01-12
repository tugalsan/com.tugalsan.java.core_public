module com.tugalsan.java.core.servlet.url {
    requires javax.servlet.api;
    requires java.desktop;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.function;    
    requires com.tugalsan.java.core.file.common;
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.file.json;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.network;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.file.html;
    exports com.tugalsan.java.core.servlet.url.client;
    exports com.tugalsan.java.core.servlet.url.server;
    exports com.tugalsan.java.core.servlet.url.server.handler;
}
