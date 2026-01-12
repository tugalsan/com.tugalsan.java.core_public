package com.tugalsan.java.core.console.jdk.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.stream;
import java.util.stream.*;

public class TS_ConsoleLine {

    public String commandName;
    public CharSequence[] commadArgs;

    private TS_ConsoleLine(String... mainArguments) {
        commandName = mainArguments[0];
        this.commadArgs = TGS_ListCastUtils.toArrayCharSequence(
                TGS_StreamUtils.toLst(
                        IntStream.range(0, mainArguments.length)
                                .filter(i -> i != 0)
                                .mapToObj(i -> (CharSequence) mainArguments[i])
                )
        );
    }

    public static TS_ConsoleLine of(String... mainArguments) {
        return new TS_ConsoleLine(mainArguments);
    }
}
