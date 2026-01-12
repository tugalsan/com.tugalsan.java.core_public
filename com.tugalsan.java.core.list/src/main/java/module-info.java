module com.tugalsan.java.core.list {
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.shape;
    
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.cast;
    exports com.tugalsan.java.core.list.client;
    exports com.tugalsan.java.core.list.server;
}
