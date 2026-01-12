module com.tugalsan.java.core.sql.basic {
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.sql.col.typed;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.insert;
    requires com.tugalsan.java.core.sql.max;
    requires com.tugalsan.java.core.sql.select;
    requires com.tugalsan.java.core.sql.tbl;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.delete;
    requires com.tugalsan.java.core.sql.where;
    exports com.tugalsan.java.core.sql.basic.client;
    exports com.tugalsan.java.core.sql.basic.server;
}
