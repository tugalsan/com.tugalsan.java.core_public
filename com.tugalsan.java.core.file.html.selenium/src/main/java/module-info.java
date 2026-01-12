module com.tugalsan.java.core.file.html.selenium {
    requires java.xml;
    requires org.seleniumhq.selenium.manager;
    requires org.seleniumhq.selenium.support;
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.edge_driver;
    requires org.seleniumhq.selenium.chrome_driver;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.thread;
    requires com.tugalsan.java.core.url;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.function;
    exports com.tugalsan.java.core.file.html.selenium.server;
}
