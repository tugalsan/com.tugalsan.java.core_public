module com.tugalsan.java.core.font {
    requires java.desktop;
    requires com.tugalsan.java.core.file;
    
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.function;
    
    exports com.tugalsan.java.core.font.client;
    exports com.tugalsan.java.core.font.server;
}
