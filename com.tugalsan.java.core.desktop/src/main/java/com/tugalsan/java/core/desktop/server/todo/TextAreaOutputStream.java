package com.tugalsan.java.core.desktop.server.todo;

import module com.tugalsan.java.core.console.jdk;
import module java.desktop;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.regex.*;
import java.util.List;

//https://stackoverflow.com/questions/342990/create-java-console-inside-a-gui-panel#comment43886110_343007
@Deprecated //TODO
public class TextAreaOutputStream extends OutputStream {

    public static void main() throws InterruptedException {
        var frame = new JFrame();
        frame.add(new JLabel(" Outout"), BorderLayout.NORTH);

        var ta = new JTextArea();
        var taos = new TextAreaOutputStream(ta, 60);
        TS_ConsoleUtils.bindToOutputStream(taos);

        frame.add(new JScrollPane(ta));

        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);

        for (var i = 0; i < 100; i++) {
            System.out.println(i);
            Thread.sleep(500);
        }
    }
    private byte[] oneByte;
    private Appender appender;

    public TextAreaOutputStream(JTextArea txtara) {
        this(txtara, 1000);
    }

    public TextAreaOutputStream(JTextArea txtara, int maxlin) {
        this(txtara, maxlin, null);
    }

    public TextAreaOutputStream(JTextArea txtara, int maxlin, Pattern rmvptn) {
        if (maxlin < 1) {
            throw new IllegalArgumentException("TextAreaOutputStream maximum lines must be positive (value=" + maxlin + ")");
        }
        oneByte = new byte[1];
        appender = new Appender(txtara, maxlin, rmvptn);
    }

    public synchronized void clear() {
        if (appender != null) {
            appender.clear();
        }
    }

    @Override
    public synchronized void close() {
        appender = null;
    }

    @Override
    public synchronized void flush() {
    }

    @Override
    public synchronized void write(int val) {
        oneByte[0] = (byte) val;
        write(oneByte, 0, 1);
    }

    @Override
    public synchronized void write(byte[] ba) {
        write(ba, 0, ba.length);
    }

    @Override
    public synchronized void write(byte[] ba, int str, int len) {
        if (appender != null) {
            appender.append(bytesToString(ba, str, len));
        }
    }

    @SuppressWarnings("DM_DEFAULT_ENCODING")
    static private String bytesToString(byte[] ba, int str, int len) {
        return new String(ba, str, len, StandardCharsets.UTF_8);
    }

    static class Appender implements Runnable {

        private final StringBuilder line = new StringBuilder(1000);
        private final List<String> lines = new ArrayList();// lines waiting to be appended
        private final LinkedList<Integer> lengths = new LinkedList();// lengths of each line within text area

        private final JTextArea textArea;
        private final int maxLines; // maximum lines allowed in text area
        private final Pattern rmvPattern;

        private boolean clear;
        private boolean queue;

        Appender(JTextArea txtara, int maxlin, Pattern rmvptn) {
            textArea = txtara;
            maxLines = maxlin;
            rmvPattern = rmvptn;

            clear = false;
            queue = true;
        }

        synchronized void append(String val) {
            var eol = val.endsWith(EOL1) || val.endsWith(EOL2);

            line.append(val);
            while (line.length() > LINE_MAX) {
                emitLine(line.substring(0, LINE_MAX) + EOL1);
                line.replace(0, LINE_MAX, "[>>] ");
            }
            if (eol) {
                emitLine(line.toString());
                line.setLength(0);
            }
        }

        private void emitLine(String lin) {
            if (lines.size() > 10_000) {
                lines.clear();
                lines.add("<console-overflowed>\n");
            } else {
                if (rmvPattern != null) {
                    lin = rmvPattern.matcher(lin).replaceAll("");
                }
                lines.add(lin);
            }
            if (queue) {
                queue = false;
                EventQueue.invokeLater(this);
            }
        }

        synchronized void clear() {
            clear = true;
            if (queue) {
                queue = false;
                EventQueue.invokeLater(this);
            }
        }

        @Override
        public synchronized void run() {// MUST BE THE ONLY METHOD THAT TOUCHES textArea!
            var don = 0;

            if (clear) {
                lengths.clear();
                lines.clear();
                textArea.setText("");
                clear = false;
            }

            for (var lin : lines) {
                don += 1;
                lengths.addLast(lin.length());
                if (lengths.size() >= maxLines) {
                    textArea.replaceRange("", 0, lengths.removeFirst());
                }
                textArea.append(lin);
                if (don >= 100) {
                    break;
                }
            }
            if (don == lines.size()) {
                lines.clear();
                queue = true;
            } else {
                lines.subList(0, don).clear();
                EventQueue.invokeLater(this);
            }
        }

        static private final String EOL1 = "\n";
        static private final String EOL2 = System.getProperty("line.separator", EOL1);
        static private final int LINE_MAX = 1000;

    }
}
