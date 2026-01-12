module com.tugalsan.java.core.sql.delete {
    requires java.sql;
    requires com.tugalsan.java.core.function;
    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.where;
    requires com.tugalsan.java.core.sql.conn;
    exports com.tugalsan.java.core.sql.delete.server;
}
