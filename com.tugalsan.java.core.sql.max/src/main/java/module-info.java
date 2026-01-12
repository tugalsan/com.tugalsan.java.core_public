module com.tugalsan.java.core.sql.max {
    requires java.sql;
    requires com.tugalsan.java.core.time;
    
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.tuple;
    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.select;
    requires com.tugalsan.java.core.sql.where;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.resultset;
    exports com.tugalsan.java.core.sql.max.server;
}
