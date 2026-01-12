package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import java.io.*;

public class TS_DesktopConsoleUtils {

    private TS_DesktopConsoleUtils() {

    }

//    public static Console create() {//GRRALVM DOES NOT LIKE U
//        return System.console();
//    }
    public static TGS_UnionExcuse<String> readLine() {
        return TGS_FuncMTCUtils.call(() -> {
//            if (System.console() != null) {//GRRALVM DOES NOT LIKE U
//                return Optional.of(System.console().readLine());
//            }
            var reader = new BufferedReader(new InputStreamReader(System.in));
            return TGS_UnionExcuse.of(reader.readLine());
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public static TGS_UnionExcuse<String> readPassword() {
        return TGS_FuncMTCUtils.call(() -> {
//            if (System.console() != null) {//GRRALVM DOES NOT LIKE U
//                return Optional.of(new String(System.console().readPassword()));
//            }
            return readLine();
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }
}
