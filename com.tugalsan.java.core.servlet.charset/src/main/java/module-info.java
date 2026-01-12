module com.tugalsan.java.core.servlet.charset {
    requires jakarta.servlet;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.log;    
    exports com.tugalsan.java.core.servlet.charset.server;
}
