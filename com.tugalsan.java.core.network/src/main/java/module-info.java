module com.tugalsan.java.core.network {
    //requires java.xml;
    requires java.xml.bind;
    requires javax.servlet.api;
    requires jcifs;
    requires com.tugalsan.java.core.os;    
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.random;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.charset;
    exports com.tugalsan.java.core.network.client;
    exports com.tugalsan.java.core.network.server;
}
