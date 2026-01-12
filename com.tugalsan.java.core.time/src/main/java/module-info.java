module com.tugalsan.java.core.time {
    requires com.sun.jna;
    requires com.sun.jna.platform;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.string;
    exports com.tugalsan.java.core.time.client;
    exports com.tugalsan.java.core.time.server;
}
