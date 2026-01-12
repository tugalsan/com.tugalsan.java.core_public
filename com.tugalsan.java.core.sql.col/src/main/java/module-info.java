module com.tugalsan.java.core.sql.col {
    requires java.sql;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.list;
    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.db;
    requires com.tugalsan.java.core.sql.resultset;
    requires com.tugalsan.java.core.sql.col.typed;
    exports com.tugalsan.java.core.sql.col.server;
}
