module com.tugalsan.java.core.log {
    requires javax.servlet.api;
    requires org.fusesource.jansi;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.function;
    exports com.tugalsan.java.core.log.client;
    exports com.tugalsan.java.core.log.server;
}
