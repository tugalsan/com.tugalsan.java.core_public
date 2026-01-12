module com.tugalsan.java.core.sql.tbl {
    requires java.sql;
    requires com.tugalsan.java.core.list;
    
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.sql.col.typed;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.select;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.resultset;
    exports com.tugalsan.java.core.sql.tbl.server;
}
