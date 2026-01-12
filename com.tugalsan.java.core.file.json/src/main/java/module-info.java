module com.tugalsan.java.core.file.json {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;    
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.file.txt;
    exports com.tugalsan.java.core.file.json.client;
    exports com.tugalsan.java.core.file.json.server;
}
