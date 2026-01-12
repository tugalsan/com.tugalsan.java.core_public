module com.tugalsan.java.core.servlet.upload {
    requires javax.servlet.api;    
//    requires org.apache.commons.fileupload2.core;;
//    requires org.apache.commons.fileupload2.javax;
    requires commons.fileupload;
    requires org.apache.commons.io;    
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.file.json;
    requires com.tugalsan.java.core.file.txt;    
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.tomcat;
    exports com.tugalsan.java.core.servlet.upload.client;
    exports com.tugalsan.java.core.servlet.upload.server;
}


