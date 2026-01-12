module com.tugalsan.java.core.sql.backup {
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.file;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.file.txt;
    requires com.tugalsan.java.core.file.zip;
    requires com.tugalsan.java.core.sql.conn;
    exports com.tugalsan.java.core.sql.backup.server;
}
