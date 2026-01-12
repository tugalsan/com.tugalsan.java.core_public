module com.tugalsan.java.core.file.xml {
    requires java.xml;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.bytes;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.tree;
    requires com.tugalsan.java.core.string;
    exports com.tugalsan.java.core.file.xml.server.obj;
    exports com.tugalsan.java.core.file.xml.server.table;
    exports com.tugalsan.java.core.file.xml.server;
}
