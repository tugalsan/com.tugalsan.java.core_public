module com.tugalsan.java.core.sql.resultset {
    requires java.sql;
    
    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.sql.col.typed;
    requires com.tugalsan.java.core.sql.cell;
    exports com.tugalsan.java.core.sql.resultset.server;
}
