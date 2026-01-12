module com.tugalsan.java.core.thread {
    requires gwt.user;
    requires jdk.management;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.os;
    requires com.tugalsan.java.core.random;
    requires com.tugalsan.java.core.stream;
    requires com.tugalsan.java.core.union;
    requires com.tugalsan.java.core.time;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.list;
    exports com.tugalsan.java.core.thread.client;
    exports com.tugalsan.java.core.thread.server.async.await;
    exports com.tugalsan.java.core.thread.server.async.await.core;
    exports com.tugalsan.java.core.thread.server.async.builder;
    exports com.tugalsan.java.core.thread.server.async.run;
    exports com.tugalsan.java.core.thread.server.async.scheduled;
    exports com.tugalsan.java.core.thread.server.sync;
    exports com.tugalsan.java.core.thread.server.sync.rateLimited;
    exports com.tugalsan.java.core.thread.server.sync.lockLimited;
}
