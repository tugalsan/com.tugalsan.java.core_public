module com.tugalsan.java.core.profile {
    requires java.sql;
    requires javax.servlet.api;
    requires javamelody.core;
    requires com.tugalsan.java.core.url;    
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.function;
    exports com.tugalsan.java.core.profile.client;
    exports com.tugalsan.java.core.profile.server.melody;
}
