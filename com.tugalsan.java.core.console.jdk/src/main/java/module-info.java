module com.tugalsan.java.core.console.jdk {
    //requires commons.cli;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.input;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.math;
    requires com.tugalsan.java.core.stream;
    exports com.tugalsan.java.core.console.jdk.client;
    exports com.tugalsan.java.core.console.jdk.server;
}