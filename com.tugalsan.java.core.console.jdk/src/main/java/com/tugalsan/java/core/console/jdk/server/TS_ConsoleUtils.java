package com.tugalsan.java.core.console.jdk.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.stream;
import module com.tugalsan.java.core.input;
import module com.tugalsan.java.core.console.jdk;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class TS_ConsoleUtils {

    private TS_ConsoleUtils() {

    }

    final private static TS_Log d = TS_Log.of(TS_ConsoleUtils.class);

    public static void bindToOutputStream(OutputStream os) {
        var con = new PrintStream(os);
        System.setOut(con);
        System.setErr(con);
    }

    public static void clearScreen() {
        IO.print("\033[H\033[2J");
        System.out.flush();// I need it, as I did not use println above.
    }

    public static void mainLoop(TGS_CharSetLocaleTypes language, List<String> quitCommands, List<String> clearScreen, List<TGS_ConsoleOption> runOptions, final CharSequence... initCmdAndArguments) {
        var runHelp = TGS_ConsoleOption.of(language, (cmd, args) -> {
            runOptions.forEach(ro -> d.cr("help", ro.toString()));
        }, TGS_ListUtils.of("h", "help"));
        var runQuit = TGS_ConsoleOption.of(language, (cmd, args) -> {
            //NOTHING
        }, quitCommands);
        var runCls = TGS_ConsoleOption.of(language, (cmd, args) -> {
            //NOTHING
        }, clearScreen);
        var runUnknown = TGS_ConsoleOption.of(language, (cmd, args) -> {
            d.ce("mainLoop", "ERROR: dont know what 2 do with args:");
            d.ci("mainLoop", "firstArg", cmd);
            IntStream.range(0, args.size()).forEachOrdered(i -> {
                d.ci("mainLoop", "restArgs", i, args.get(i));
            });
        });
        TS_ConsoleUtils.clearScreen();
        if (initCmdAndArguments != null && initCmdAndArguments.length > 0) {
            var fullInitCmd = String.join(" ", initCmdAndArguments);
            var fullInitCmd_ParsedLine = TGS_ConsoleUtils.parseLine(fullInitCmd);
            if (!fullInitCmd_ParsedLine.isEmpty()) {
                var fullInitCmd_ParsedList = TGS_ListUtils.sliceFirstToken(TGS_StreamUtils.toLst(fullInitCmd_ParsedLine.stream().map(s -> (CharSequence) s)));
                if (runQuit.is(fullInitCmd_ParsedList.value0)) {
                    return;
                }
                if (runHelp.is(fullInitCmd_ParsedList.value0)) {
                    runHelp.run.run(fullInitCmd_ParsedList.value0, fullInitCmd_ParsedList.value1);
                }
                var selectedCustomRun = runOptions.stream().filter(runCustom -> runCustom.is(fullInitCmd_ParsedList.value0))
                        .findFirst().orElse(null);
                if (selectedCustomRun == null) {
                    runUnknown.run.run(fullInitCmd_ParsedList.value0, fullInitCmd_ParsedList.value1);
                } else {
                    selectedCustomRun.run.run(fullInitCmd_ParsedList.value0, fullInitCmd_ParsedList.value1);
                }
            }
        }
        while (true) {
            d.cr("main", "newCommand:");
            var line = TS_InputKeyboardUtils.readLineFromConsole().trim();
            TS_ConsoleUtils.clearScreen();
            d.cr("main", "givenCommand", line);
            var parsedLine = TGS_ConsoleUtils.parseLine(line);
            var parsedList = TGS_ListUtils.sliceFirstToken(TGS_StreamUtils.toLst(parsedLine.stream().map(s -> (CharSequence) s)));
            if (runQuit.is(parsedList.value0)) {
                return;
            }
            if (runCls.is(parsedList.value0)) {
                continue;
            }
            if (runHelp.is(parsedList.value0)) {
                runHelp.run.run(parsedList.value0, parsedList.value1);
            }
            var selectedCustomRun = runOptions.stream().filter(runCustom -> runCustom.is(parsedList.value0))
                    .findFirst().orElse(null);
            if (selectedCustomRun == null) {
                runUnknown.run.run(parsedList.value0, parsedList.value1);
            } else {
                selectedCustomRun.run.run(parsedList.value0, parsedList.value1);
            }
        }
    }

}
