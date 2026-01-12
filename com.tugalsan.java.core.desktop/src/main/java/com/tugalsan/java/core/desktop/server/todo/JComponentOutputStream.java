package com.tugalsan.java.core.desktop.server.todo;

import module com.tugalsan.java.core.console.jdk;
import module com.tugalsan.java.core.loremipsum;
import module com.tugalsan.java.core.thread;
import module java.desktop;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.*;
import java.util.stream.*;

//https://stackoverflow.com/questions/342990/create-java-console-inside-a-gui-panel#comment43886110_343007
@Deprecated //TODO
public class JComponentOutputStream extends OutputStream {

    public static void main() throws InterruptedException {
        var kt = TS_ThreadSyncTrigger.of("main");
        var frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new JLabel(" Outout"), BorderLayout.CENTER);
        frame.add(new JScrollPane(pane()));

        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);

        TS_ThreadAsyncRun.now(kt, _kt -> a());
        TS_ThreadAsyncRun.now(kt, _kt -> a());
        TS_ThreadAsyncRun.now(kt, _kt -> a());
        TS_ThreadAsyncRun.now(kt, _kt -> a());
        TS_ThreadAsyncRun.now(kt, _kt -> a());
    }

    static void a() {
        IntStream.range(0, 1000).forEachOrdered(i -> {
            System.out.println(TGS_LoremIpsum.getWords(5, 20));
            TS_ThreadSyncWait.milliseconds200();
        });
    }

    public static JComponent pane() {
        var ta = new JTextArea();
        var cos = new JComponentOutputStream(ta, new JComponentHandler() {
            private final StringBuilder sb = new StringBuilder();

            @Override
            public void setText(JComponent swingComponent, String text) {
                sb.delete(0, sb.length());
                append(swingComponent, text);
            }

            @Override
            public void replaceRange(JComponent swingComponent, String text, int start, int end) {
                sb.replace(start, end, text);
                redrawTextOf(swingComponent);
            }

            @Override
            public void append(JComponent swingComponent, String text) {
                sb.append(text);
                redrawTextOf(swingComponent);
            }

            private void redrawTextOf(JComponent swingComponent) {
//                ((JLabel) swingComponent).setText("<html><pre>" + sb.toString() + "</pre></html>");
                ta.setText(sb.toString());
                ta.setCaretPosition(ta.getDocument().getLength());
            }
        });

        TS_ConsoleUtils.bindToOutputStream(cos);
        return ta;
    }

    private byte[] oneByte; // array for write(int val);
    private Appender appender; // most recent action

    final private Lock jcosLock = new ReentrantLock();

    public JComponentOutputStream(JComponent txtara, JComponentHandler handler) {
        this(txtara, 1000, handler);
    }

    public JComponentOutputStream(JComponent txtara, int maxlin, JComponentHandler handler) {
        if (maxlin < 1) {
            throw new IllegalArgumentException("JComponentOutputStream maximum lines must be positive (value=" + maxlin + ")");
        }
        oneByte = new byte[1];
        appender = new Appender(txtara, maxlin, handler);
    }

    /**
     * Clear the current console text area.
     */
    public void clear() {
        jcosLock.lock();
        try {
            if (appender != null) {
                appender.clear();
            }
        } finally {
            jcosLock.unlock();
        }
    }

    @Override
    public void close() {
        jcosLock.lock();
        try {
            appender = null;
        } finally {
            jcosLock.unlock();
        }
    }

    @Override
    public void flush() {
        // sstosLock.lock();
        // try {
        // // TODO: Add necessary code here...
        // } finally {
        // sstosLock.unlock();
        // }
    }

    @Override
    public void write(int val) {
        jcosLock.lock();
        try {
            oneByte[0] = (byte) val;
            write(oneByte, 0, 1);
        } finally {
            jcosLock.unlock();
        }
    }

    @Override
    public void write(byte[] ba) {
        jcosLock.lock();
        try {
            write(ba, 0, ba.length);
        } finally {
            jcosLock.unlock();
        }
    }

    @Override
    public void write(byte[] ba, int str, int len) {
        jcosLock.lock();
        try {
            if (appender != null) {
                appender.append(bytesToString(ba, str, len));
            }
        } finally {
            jcosLock.unlock();
        }
    }

    static private String bytesToString(byte[] ba, int str, int len) {
        return new String(ba, str, len, StandardCharsets.UTF_8);
    }

    static class Appender implements Runnable {

        private final JComponent swingComponent;
        private final int maxLines; // maximum lines allowed in text area
        private final LinkedList<Integer> lengths; // length of lines within
        // text area
        private final List<String> values; // values waiting to be appended

        private int curLength; // length of current line
        private boolean clear;
        private boolean queue;

        private final Lock appenderLock;

        private final JComponentHandler handler;

        Appender(JComponent cpt, int maxlin, JComponentHandler hndlr) {
            appenderLock = new ReentrantLock();

            swingComponent = cpt;
            maxLines = maxlin;
            lengths = new LinkedList();
            values = new ArrayList();

            curLength = 0;
            clear = false;
            queue = true;

            handler = hndlr;
        }

        void append(String val) {
            appenderLock.lock();
            try {
                values.add(val);
                if (queue) {
                    queue = false;
                    EventQueue.invokeLater(this);
                }
            } finally {
                appenderLock.unlock();
            }
        }

        void clear() {
            appenderLock.lock();
            try {

                clear = true;
                curLength = 0;
                lengths.clear();
                values.clear();
                if (queue) {
                    queue = false;
                    EventQueue.invokeLater(this);
                }
            } finally {
                appenderLock.unlock();
            }
        }

        // MUST BE THE ONLY METHOD THAT TOUCHES the JComponent!
        @Override
        public void run() {
            appenderLock.lock();
            try {
                if (clear) {
                    handler.setText(swingComponent, "");
                }
                for (var val : values) {
                    curLength += val.length();
                    if (val.endsWith(EOL1) || val.endsWith(EOL2)) {
                        if (lengths.size() >= maxLines) {
                            handler.replaceRange(swingComponent, "", 0, lengths.removeFirst());
                        }
                        lengths.addLast(curLength);
                        curLength = 0;
                    }
                    handler.append(swingComponent, val);
                }

                values.clear();
                clear = false;
                queue = true;
            } finally {
                appenderLock.unlock();
            }
        }

        static private final String EOL1 = "\n";
        static private final String EOL2 = System.getProperty("line.separator", EOL1);
    }

    public interface JComponentHandler {

        void setText(JComponent swingComponent, String text);

        void replaceRange(JComponent swingComponent, String text, int start, int end);

        void append(JComponent swingComponent, String text);
    }

}
/* END PUBLIC CLASS */
