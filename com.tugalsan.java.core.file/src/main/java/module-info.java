module com.tugalsan.java.core.file {
    requires java.xml.bind;
    requires org.apache.commons.text;
    requires com.tugalsan.java.core.list;    
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.time;
    exports com.tugalsan.java.core.file.client;
    exports com.tugalsan.java.core.file.server;
    exports com.tugalsan.java.core.file.server.watch;
}
