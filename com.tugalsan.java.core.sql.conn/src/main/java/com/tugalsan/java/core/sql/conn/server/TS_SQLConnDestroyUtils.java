package com.tugalsan.java.core.sql.conn.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module java.sql;
import java.util.*;

public class TS_SQLConnDestroyUtils {

    final private static TS_Log d = TS_Log.of(TS_SQLConnDestroyUtils.class);

    private TS_SQLConnDestroyUtils() {

    }

    @Deprecated //WHY TO USE. NOT GOOD WITH MULTI WAR PROJECTS
    public static void destroy(TS_SQLConnAnchor... anchors) {
        var isMysqlVariant = Arrays.stream(anchors)
                .anyMatch(anchor -> anchor.config.method == TS_SQLConnMethodUtils.METHOD_MARIADB() || anchor.config.method == TS_SQLConnMethodUtils.METHOD_MYSQL());
        if (isMysqlVariant) {
//            try {
//                AbandonedConnectionCleanupThread.shutdown();
//            } catch (InterruptedException e) {
//            }
        }
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            TGS_FuncMTCUtils.run(() -> {
                DriverManager.deregisterDriver(driver);
                d.cr("Driver deregistered", driver);
            }, e -> d.ce("Error deregistering driver!", driver, e.getMessage()));
        });
    }
}
