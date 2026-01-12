module com.tugalsan.java.core.sql.conn {
    requires java.sql;
    requires tomcat.jdbc;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.profile;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.file.obj;
    requires com.tugalsan.java.core.file.json;
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.sql.col.typed;
    requires com.tugalsan.java.core.sql.sanitize;
    requires com.tugalsan.java.core.sql.resultset;
    exports com.tugalsan.java.core.sql.conn.server;
}
