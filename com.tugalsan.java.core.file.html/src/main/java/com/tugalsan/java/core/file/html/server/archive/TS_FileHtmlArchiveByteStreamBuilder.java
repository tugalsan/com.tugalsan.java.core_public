package com.tugalsan.java.core.file.html.server.archive;


import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.url;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class TS_FileHtmlArchiveByteStreamBuilder {

    public byte[] data = new byte[1];
    int len;

    public TS_FileHtmlArchiveByteStreamBuilder append(int b) {
        if (len == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[len++] = (byte) b;
        return (this);
    }

    public TS_FileHtmlArchiveByteStreamBuilder appendUTF8(CharSequence str) {
        var b = StandardCharsets.UTF_8.encode(str.toString());
        while (b.hasRemaining()) {
            append(b.get());
        }
        return (this);
    }

    public TS_FileHtmlArchiveByteStreamBuilder append(byte[] s) {
        for (var i = 0; i < s.length; i++) {
            append(s[i]);
        }
        return (this);
    }

    public TS_FileHtmlArchiveByteStreamBuilder() {
    }

    public TS_FileHtmlArchiveByteStreamBuilder(byte[] d) {
        data = d;
        len = d.length;
    }

    public TS_FileHtmlArchiveByteStreamBuilder append(InputStream in) {
        return (append(in, Integer.MAX_VALUE));
    }

    public TS_FileHtmlArchiveByteStreamBuilder append(InputStream in, int num0) {
        return TGS_FuncMTCUtils.call(() -> {
            var num = num0;
            int c;
            while (num-- > 0 && (c = in.read()) != -1) {
                append(c);
            }
            return (this);
        });
    }

    public TS_FileHtmlArchiveByteStreamBuilder append(URL url) {
        return TGS_FuncMTCUtils.call(() -> {
            try ( var in = url.openStream()) {
                append(in, Integer.MAX_VALUE);
            }
            return (this);
        });
    }

    public TS_FileHtmlArchiveByteStreamBuilder append(Path input) {
        return TGS_FuncMTCUtils.call(() -> {
            try ( var in = Files.newInputStream(input)) {
                append(in);
            }
            return (this);
        });
    }

    public void writeTo(OutputStream out) {
        TGS_FuncMTCUtils.run(() -> {
            for (var i = 0; i < len; i++) {
                out.write(data[i]);
            }
        });
    }

    public void writeTo(Path output) {
        TGS_FuncMTCUtils.run(() -> {
            try ( var o = Files.newOutputStream(output)) {
                writeTo(o);
            }
        });
    }

    public TS_FileHtmlArchiveByteStreamBuilder toDataUri(CharSequence mime) {
        data = Arrays.copyOf(data, len);
        return (new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("data:" + mime + ";base64,").append(Base64.getEncoder().encode(data)));
    }

    /**
     * Returns a byte buffer that results from decoding the current buffer as a
     * data URI.Puts the mime type in mime[0]. Returns null in case of failure.
     */
    public TS_FileHtmlArchiveByteStreamBuilder fromDataUri(String[] mime) {//SHOULD STAY STRING!
        if (!startsWithASCII("data:")) {
            return (null);
        }
        var mimeBytes = new TS_FileHtmlArchiveByteStreamBuilder();
        int i;
        for (i = 5; i < len && i < 100 && data[i] != ','; i++) {
            mimeBytes.append(data[i]);
        }
        mime[0] = mimeBytes.toString();
        if (!mime[0].endsWith(";base64")) {
            return (null);
        }
        mime[0] = mime[0].substring(0, mime[0].length() - 7);
        return (new TS_FileHtmlArchiveByteStreamBuilder(Base64.getDecoder().decode(Arrays.copyOfRange(data, i + 1, len))));
    }

    @Override
    public String toString() {
        return (StandardCharsets.UTF_8.decode(ByteBuffer.wrap(data, 0, len)).toString());
    }

    /**
     * Reads until any of the (ASCII) delimiters appears. Reads, but does not
     * append the delimiter.
     */
    public TS_FileHtmlArchiveByteStreamBuilder appendASCII(InputStream in, CharSequence delimiters) {
        return TGS_FuncMTCUtils.call(() -> {
            int c;
            var delimitersStr = delimiters.toString();
            while ((c = in.read()) != -1 && delimitersStr.indexOf(c) == -1) {
                append(c);
            }
            return (this);
        });
    }

    public boolean startsWithASCII(CharSequence s) {
        return -1 == IntStream.range(0, s.length()).filter(i -> i > len || data[i] != s.charAt(i)).findAny().orElse(-1);
    }

    public static TS_FileHtmlArchiveByteStreamBuilder forUrlOrFile(CharSequence source) {
        return TGS_FuncMTCUtils.call(() -> {
            var sourceStr = source.toString();
            if (TGS_UrlUtils.isValidUrl(TGS_Url.of(sourceStr))) {
                return (new TS_FileHtmlArchiveByteStreamBuilder().append(URI.create(sourceStr).toURL()));
            } else {
                return (new TS_FileHtmlArchiveByteStreamBuilder().append(Path.of(sourceStr)));
            }
        });
    }
}
