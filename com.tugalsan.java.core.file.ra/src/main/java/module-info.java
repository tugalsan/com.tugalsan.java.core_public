module com.tugalsan.java.core.file.ra {
    requires com.tugalsan.java.core.bytes;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.stream;    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.function;
    exports com.tugalsan.java.core.file.ra.server.object;
    exports com.tugalsan.java.core.file.ra.server.simple;
    exports com.tugalsan.java.core.file.ra.server.table;
}
