module com.tugalsan.java.core.email {
    requires jakarta.activation;
    requires jakarta.mail;
    //requires outlook-message-parser;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.union;    
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.log;
    exports com.tugalsan.java.core.email.client;
    exports com.tugalsan.java.core.email.server;
}
