module com.tugalsan.java.core.serialcom {
    requires com.fazecast.jSerialComm;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.function;
    exports com.tugalsan.java.core.serialcom.server;
    exports com.tugalsan.java.core.serialcom.server.utils;
}
