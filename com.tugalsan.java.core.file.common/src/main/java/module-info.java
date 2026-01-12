module com.tugalsan.java.core.file.common {
    requires java.desktop;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.font;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.list;
    
    requires com.tugalsan.java.core.time;
    exports com.tugalsan.java.core.file.common.client;
    exports com.tugalsan.java.core.file.common.server;
}
