module com.tugalsan.java.core.sql.duplicate {
    requires java.sql;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.sql.cellgen;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.where;
    requires com.tugalsan.java.core.sql.sanitize;
    exports com.tugalsan.java.core.sql.duplicate.server;
}
