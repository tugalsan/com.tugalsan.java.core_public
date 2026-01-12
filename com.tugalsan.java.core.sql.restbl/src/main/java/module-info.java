module com.tugalsan.java.core.sql.restbl {
    
    
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.thread;
    
    requires com.tugalsan.java.core.sql.resultset;
    requires com.tugalsan.java.core.sql.select;
    exports com.tugalsan.java.core.sql.restbl.server;
}
