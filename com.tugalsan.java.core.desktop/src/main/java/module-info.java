module com.tugalsan.java.core.desktop {
    requires java.desktop;
    requires com.tugalsan.java.core.console.jdk;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.loremipsum;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.shape;
    requires com.tugalsan.java.core.function;    
    requires com.tugalsan.java.core.cast;
    requires com.tugalsan.java.core.charset;
    exports com.tugalsan.java.core.desktop.server;
}