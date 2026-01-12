module com.tugalsan.java.core.os {
    requires java.prefs;
    requires jdk.management;
    requires com.github.oshi;
    requires com.sun.jna.platform;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.random;
    requires com.tugalsan.java.core.stream;
    exports com.tugalsan.java.core.os.server;
}
