module com.tugalsan.java.core.captcha {
    requires java.desktop;
    requires gwt.user;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.network;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.random;
    requires com.tugalsan.java.core.servlet.url;
    exports com.tugalsan.java.core.captcha.client;
    exports com.tugalsan.java.core.captcha.server;
}
