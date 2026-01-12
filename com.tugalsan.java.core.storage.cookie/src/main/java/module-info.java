module com.tugalsan.java.core.storage.cookie {
    requires javax.servlet.api;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.list;
    exports com.tugalsan.java.core.storage.cookie.client;
    exports com.tugalsan.java.core.storage.cookie.server;
}
