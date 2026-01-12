module com.tugalsan.java.core.url {
    requires gwt.user;
    requires java.net.http;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.network;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.crypto;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.random;
    exports com.tugalsan.java.core.url.client;
    exports com.tugalsan.java.core.url.client.builder;
    exports com.tugalsan.java.core.url.client.parser;
    exports com.tugalsan.java.core.url.server;
}
