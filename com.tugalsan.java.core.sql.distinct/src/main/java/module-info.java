module com.tugalsan.java.core.sql.distinct {
    requires java.sql;
    
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.cell;
    requires com.tugalsan.java.core.sql.order;
    requires com.tugalsan.java.core.sql.col.typed;
    requires com.tugalsan.java.core.sql.resultset;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.where;
    requires com.tugalsan.java.core.sql.select;
    requires com.tugalsan.java.core.sql.group;
    exports com.tugalsan.java.core.sql.distinct.server;
}
