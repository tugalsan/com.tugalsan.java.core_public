module com.tugalsan.java.core.gui.visualization {
    requires gwt.user;
    requires gwt.visualization;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.random;
    exports com.tugalsan.java.core.gui.visualization.client;
    exports com.tugalsan.java.core.gui.visualization.server;
}
