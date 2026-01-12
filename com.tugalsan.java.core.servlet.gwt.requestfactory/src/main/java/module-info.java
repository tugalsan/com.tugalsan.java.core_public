module com.tugalsan.java.core.servlet.gwt.requestfactory {
    requires gwt.user;
    requires javax.websocket.api;
    requires javax.servlet.api;
//    requires elemental2.promise;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.file.json;
    requires com.tugalsan.java.core.file.txt;    
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.network;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.url;
    exports com.tugalsan.java.core.servlet.gwt.requestfactory.client;
    exports com.tugalsan.java.core.servlet.gwt.requestfactory.client.ws;
    exports com.tugalsan.java.core.servlet.gwt.requestfactory.server;
}
