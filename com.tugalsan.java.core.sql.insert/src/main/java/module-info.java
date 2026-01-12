module com.tugalsan.java.core.sql.insert {
    requires java.sql;
    
    
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.file.obj;
    requires com.tugalsan.java.core.sql.col.typed;
    requires com.tugalsan.java.core.sql.cell;
    requires com.tugalsan.java.core.sql.cellgen;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.sanitize;
    exports com.tugalsan.java.core.sql.insert.server;
}
