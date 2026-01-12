module com.tugalsan.java.core.file.xlsx {
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.poi.scratchpad;
    requires org.apache.commons.io;
    
    requires java.desktop;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.charset;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.cast;
    
    requires com.tugalsan.java.core.math;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.file.html;
    requires com.tugalsan.java.core.file.common;
    exports com.tugalsan.java.core.file.xlsx.server;
}
