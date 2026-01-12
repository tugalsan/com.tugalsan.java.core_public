module com.tugalsan.java.core.file.properties {
    
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.string;
    //exports com.tugalsan.java.core.file.properties.client; NOT GWT able
    exports com.tugalsan.java.core.file.properties.server;
}
