module com.tugalsan.java.core.sql.db {
    requires java.sql;
    requires com.tugalsan.java.core.function;
    
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.resultset;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.conn;
    exports com.tugalsan.java.core.sql.db.server;
}
