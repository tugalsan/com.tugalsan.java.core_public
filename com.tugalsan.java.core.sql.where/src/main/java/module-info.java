module com.tugalsan.java.core.sql.where {
    requires java.sql;
    requires com.tugalsan.java.core.function;
    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.sql.sanitize;
    exports com.tugalsan.java.core.sql.where.server;
    exports com.tugalsan.java.core.sql.where.server.cond;
}
