module com.tugalsan.java.core.tomcat {
    requires java.sql;
    requires javax.servlet.api;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.file;    
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.thread;
    exports com.tugalsan.java.core.tomcat.server;
}
