module com.tugalsan.java.core.math {
    requires jdk.incubator.vector;
    requires combinatoricslib3;
    requires commons.math3;
    requires com.tugalsan.java.core.tuple;
    requires com.tugalsan.java.core.function;
    requires com.tugalsan.java.core.list;
    requires com.tugalsan.java.core.log;
    requires com.tugalsan.java.core.string;
    requires com.tugalsan.java.core.stream;
    exports com.tugalsan.java.core.math.client;
    exports com.tugalsan.java.core.math.server;
}
