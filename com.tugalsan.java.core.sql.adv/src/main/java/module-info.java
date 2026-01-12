module com.tugalsan.java.core.sql.adv {
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.log;
    
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.resultset;
    requires com.tugalsan.java.core.sql.select;
    requires com.tugalsan.java.core.sql.update;
    requires com.tugalsan.java.core.sql.conn;
    requires com.tugalsan.java.core.sql.col.typed;
    exports com.tugalsan.java.core.sql.adv.server;
}
