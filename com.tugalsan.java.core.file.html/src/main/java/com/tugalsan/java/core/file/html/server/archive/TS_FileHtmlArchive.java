package com.tugalsan.java.core.file.html.server.archive;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.url;
import java.io.File;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

public class TS_FileHtmlArchive {

    /**
     * Resolves a remote filename wrt a remote context (file or URL)
     */
    public static String remoteName(CharSequence context, CharSequence fileName) {
        return TGS_FuncMTCUtils.call(() -> {
            var contextStr = context.toString();
            var fileNameStr = fileName.toString();
            if (TGS_UrlUtils.isValidUrl(TGS_Url.of(contextStr))) {
                return (new URL(new URL(contextStr), fileNameStr).toString());
            }
            if (TGS_UrlUtils.isValidUrl(TGS_Url.of(fileNameStr))) {
                return (fileNameStr);
            }
            return (new File(new File(contextStr).getParent(), fileNameStr).toString());
        });
    }

    /**
     * Different types of links that we can encounter
     */
    public static enum LinkType {
        NONE(";lanv;lna;v;nv;urunrenunvi", false) {
            @Override
            public void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result) {
            }
        },
        SRC(" src=", false) {
            @Override
            public void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result) {
                TGS_FuncMTCUtils.run(() -> {
                    var c = in.read();
                    while (c == ' ') {
                        c = in.read();
                    }
                    switch (c) {
                        case '\'' -> {
                            result.appendASCII(in, "'");
                            return;
                        }
                        case '\"' -> {
                            result.appendASCII(in, "\"");
                            return;
                        }
                        default -> {
                            result.append(c);
                            while ((c = in.read()) != -1 && !Character.isWhitespace(c) && c != '>') {
                                result.append(c);
                            }
                            lastChar = c;
                            return;
                        }
                    }
                });
            }
        },
        HREF(" href=", false) {
            @Override
            public void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result) {
                TGS_FuncMTCUtils.run(() -> SRC.parse(in, result));
            }
        },
        LINK("<link ", false) {
            @Override
            public void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result) {
                TGS_FuncMTCUtils.run(() -> result.appendASCII(in, ">"));
            }
        },
        IMPORT("@import url(", true) {
            @Override
            public void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result) {
                TGS_FuncMTCUtils.run(() -> URL.parse(in, result));
            }
        },
        URL("url(", true) {
            @Override
            public void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result) {
                TGS_FuncMTCUtils.run(() -> {
                    var c = in.read();
                    while (c == ' ') {
                        c = in.read();
                    }
                    switch (c) {
                        case '\'' -> {
                            result.appendASCII(in, "'");
                            new TS_FileHtmlArchiveByteStreamBuilder().appendASCII(in, ")");
                            return;
                        }
                        case '\"' -> {
                            result.appendASCII(in, "\"");
                            new TS_FileHtmlArchiveByteStreamBuilder().appendASCII(in, ")");
                            return;
                        }
                        default -> {
                            result.append(c);
                            result.appendASCII(in, ")");
                            return;
                        }
                    }
                });
            }
        };
        public String tag;
        public boolean css;

        public abstract void parse(InputStream in, TS_FileHtmlArchiveByteStreamBuilder result);

        private LinkType(String s, boolean c) {
            tag = s;
            css = c;
        }
    };

    /**
     * Annoyingly, the parser may read a character too much, which we then have
     * to return next time...
     */
    protected static int lastChar = -1;

    /**
     * Reads from in, writes to out, returns linkType found, puts content into
     * result. Returns NULL for end of file.
     */
    protected static LinkType source(InputStream in, OutputStream out, TS_FileHtmlArchiveByteStreamBuilder result, boolean css) {
        return TGS_FuncMTCUtils.call(() -> {
            if (lastChar != -1) {
                out.write(lastChar);
                lastChar = -1;
            }
            var pos = new int[LinkType.values().length];
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
                for (var i = 0; i < pos.length; i++) {
                    var linkType = LinkType.values()[i];
                    if (pos[i] < linkType.tag.length() && Character.toLowerCase(c) == linkType.tag.charAt(pos[i])) {
                        pos[i]++;
                    } else if (Character.toLowerCase(c) == linkType.tag.charAt(pos[0])) {
                        pos[i] = 1;
                    } else {
                        pos[i] = 0;
                    }
                    if (pos[i] == linkType.tag.length() && (linkType.css == css || css == false)) {
                        linkType.parse(in, result);
                        return (linkType);
                    }
                }
            }
            return (LinkType.NONE);
        });
    }

    /**
     * Supported MIME types
     */
    public static final List<String> MIME() {
        return TGS_ListUtils.of(".png", "image/png", ".svg", "image/svg+xml", ".gif",
                "image/gif", ".jpeg", "image/jpeg", ".jpg", "image/jpeg", ".webm", "video/webm", ".mp4", "video/mp4",
                ".css", "text/css", ".ttf", "application/font-sfnt", ".html", "text/html", ".htm", "text/html");
    }

    /**
     * Guesses the MIME type (or returns empty string)
     */
    public static String mimeType(CharSequence path) {
        var pathStr = TGS_CharSetCast.current().toLowerCase(path);
        for (var i = 0; i < MIME().size(); i += 2) {
            if (pathStr.contains(MIME().get(i))) {
                return (MIME().get(i + 1));
            }
        }
        warn("Unknown mime type in " + pathStr);
        return ("");
    }

    /**
     * Proposes a file extension for a mime type (or .dat)
     */
    public static String fileExtension(CharSequence mimeType) {
        var mimeTypeStr = mimeType.toString();
        var pos = mimeTypeStr.lastIndexOf('/');
        if (pos == -1) {
            warn("Unknown mime type " + mimeTypeStr + ", using .dat");
            return (".dat");
        }
        mimeTypeStr = "." + TGS_CharSetCast.current().toLowerCase(mimeTypeStr.substring(pos + 1));
        if (mimeTypeStr.equals(".font-sfnt")) {
            return (".ttf");
        }
        pos = mimeTypeStr.indexOf('+');
        if (pos > 0) {
            return (mimeTypeStr.substring(0, pos));
        }
        return (mimeTypeStr);
    }

    /**
     * Extracts the style sheet path from a <link...>
     */
    public static String styleSheetPath(CharSequence link) {
        var m = Pattern.compile("href\\s*=\\s*'([^']+)'").matcher(link);
        if (m.find()) {
            return (m.group(1));
        }
        m = Pattern.compile("href\\s*=\\s*\"([^\"]+)\"").matcher(link);
        if (m.find()) {
            return (m.group(1));
        }
        m = Pattern.compile("href\\s*=\\s*([^> ]+)[> ]").matcher(link);
        if (m.find()) {
            return (m.group(1));
        }
        return (null);
    }

    /**
     * Returns the content type. Code from To Kra
     * (https://stackoverflow.com/questions/5801993/quickest-way-to-get-content-type)
     */
    public static String getContentType(CharSequence urlString) {
        return TGS_FuncMTCUtils.call(() -> {
            var url = TGS_FuncMTCUtils.call(() -> new URL(urlString.toString()));
            var con = (HttpURLConnection) url.openConnection();
            try (AutoCloseable conc = () -> con.disconnect()) {
                con.setRequestMethod("HEAD");
                if (isRedirect(con.getResponseCode())) {
                    var newUrl = con.getHeaderField("Location"); // get
                    // redirect
                    // url from
                    // "location"
                    // header
                    // field
                    return getContentType(newUrl);
                }
                var contentType = con.getContentType();
                if (contentType.indexOf(' ') != -1) {
                    contentType = contentType.substring(0, contentType.indexOf(' '));
                }
                return contentType;
            }
        });
    }

    /**
     * Check status code for redirects
     */
    protected static boolean isRedirect(int statusCode) {
        if (statusCode != HttpURLConnection.HTTP_OK) {
            if (statusCode == HttpURLConnection.HTTP_MOVED_TEMP || statusCode == HttpURLConnection.HTTP_MOVED_PERM
                    || statusCode == HttpURLConnection.HTTP_SEE_OTHER) {
                return true;
            }
        }
        return false;
    }

    /**
     * Proposes a local name for a file, chooses a name that does not yet exist
     * and creates it
     */
    public static File localNameFor(File outFile, CharSequence name, boolean overwrite) {
        return TGS_FuncMTCUtils.call(() -> {
            var nameStr = name.toString();
            if (TGS_UrlUtils.isValidUrl(TGS_Url.of(nameStr))) {
                try {
                    nameStr = new URL(nameStr).getFile().replaceAll("[/?<>\\:*|\"]", " ").trim();
                } catch (MalformedURLException e) {
                    warn(e.getMessage() + " with URL " + nameStr);
                    nameStr = outFile.getName();
                }
            }
            nameStr = new File(nameStr).getName();
            var targetFile = new File(outFile.getParent(), nameStr);
            if (targetFile.exists() && !overwrite || nameStr.length() < 5) {
                targetFile = File.createTempFile("resource-", fileExtension(mimeType(nameStr)), outFile.getParentFile());
            }
            return (targetFile);
        });
    }

    /**
     * Inlines an HTML file
     */
    public static void inline(CharSequence input, OutputStream out, boolean css, File hrefFolder) {
        TGS_FuncMTCUtils.run(() -> {
            var inputStr = input.toString();
            System.err.println("Inlining " + inputStr);
            try (var in = TGS_UrlUtils.isValidUrl(TGS_Url.of(inputStr)) ? new URL(inputStr).openStream() : new FileInputStream(inputStr)) {
                while (true) {
                    var source = new TS_FileHtmlArchiveByteStreamBuilder();
                    var linkType = source(in, out, source, css);
                    big:
                    switch (linkType) {
                        case LINK -> {
                            var link = source.toString();
                            if (link.contains("stylesheet") && styleSheetPath(link) != null) {
                                new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("/>\n<style>\n").writeTo(out);
                                inline(remoteName(inputStr, styleSheetPath(link)), out, true, hrefFolder);
                                new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("\n</style>\n").writeTo(out);
                            } else {
                                source.writeTo(out);
                                out.write('>');
                            }
                        }
                        case HREF -> {
                            if (hrefFolder != null && !source.startsWithASCII("#")) {
                                var remoteName = remoteName(inputStr, source.toString());
                                try {
                                    switch (getContentType(remoteName)) {
                                        case "text/html" -> {
                                            var localFileName = localNameFor(hrefFolder, source.toString(), true);
                                            if (!localFileName.getName().endsWith(".html")) {
                                                localFileName = new File(localFileName.getParent(),
                                                        localFileName.getName() + ".html");
                                            }
                                            if (!localFileName.exists()) {
                                                try (var out2 = new FileOutputStream(localFileName)) {
                                                    var myLastChar = lastChar;
                                                    lastChar = -1;
                                                    inline(source.toString(), out2, false, null);
                                                    lastChar = myLastChar;
                                                }
                                            }
                                            new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("\"" + localFileName.getName() + "\"").writeTo(out);
                                            break big;
                                        }
                                        case "application/pdf" -> {
                                            var localFileName = localNameFor(hrefFolder, source.toString(), true);
                                            if (!localFileName.getName().endsWith(".pdf")) {
                                                localFileName = new File(localFileName.getParent(),
                                                        localFileName.getName() + ".pdf");
                                            }
                                            if (!localFileName.exists()) {
                                                System.err.println("Downloading " + remoteName);
                                                TS_FileHtmlArchiveByteStreamBuilder.forUrlOrFile(remoteName).writeTo(localFileName.toPath());
                                            }
                                            new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("\"" + localFileName.getName() + "\"").writeTo(out);
                                            break big;
                                        }
                                    }
                                } catch (IOException e) {
                                    warn(e.toString());
                                }
                            }
                            out.write('"');
                            source.writeTo(out);
                            out.write('"');
                        }
                        case IMPORT -> {
                            new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8(")\n").writeTo(out);
                            inline(remoteName(inputStr, source.toString()), out, true, hrefFolder);
                        }
                        case URL, SRC -> {
                            if (source.startsWithASCII("#") || source.startsWithASCII("data:")) {
                                out.write('"');
                                source.writeTo(out);
                                out.write('"');
                            } else if (source.toString().contains("font") && source.toString().contains("svg")) {
                                warn("Not embedding SVG font: " + source);
                                new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("'omitted-svg-font'").writeTo(out);
                            } else if (mimeType(source.toString()).isEmpty()) {
                                out.write('"');
                                source.writeTo(out);
                                out.write('"');
                            } else {
                                out.write('"');
                                var sourceFile = remoteName(inputStr, source.toString());
                                TS_FileHtmlArchiveByteStreamBuilder.forUrlOrFile(sourceFile).toDataUri(mimeType(source.toString())).writeTo(out);
                                out.write('"');
                            }
                            if (linkType == LinkType.URL) {
                                out.write(')');
                            }
                        }
                        default -> {
                            return;
                        }
                    }
                }
            }
        });
    }

    /**
     * Produces a bundle
     */
    public static void bundle(CharSequence input, File outFile, boolean css) {
        TGS_FuncMTCUtils.run(() -> {
            var inputStr = input.toString();
            System.err.println("Bundling " + inputStr);
            try (var in = TGS_UrlUtils.isValidUrl(TGS_Url.of(inputStr)) ? new URL(inputStr).openStream() : new FileInputStream(inputStr)) {
                try (var out = new FileOutputStream(outFile)) {
                    while (true) {
                        var source = new TS_FileHtmlArchiveByteStreamBuilder();
                        var linkType = source(in, out, source, css);
                        switch (linkType) {
                            case LINK -> {
                                var link = source.toString();
                                if (link.contains("stylesheet") && styleSheetPath(link) != null) {
                                    var localStyleFile = localNameFor(outFile, styleSheetPath(link), false);
                                    bundle(remoteName(inputStr, styleSheetPath(link)), localStyleFile, true);
                                    new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("rel=\"stylesheet\" href=\"" + localStyleFile.getName() + "\" />")
                                            .writeTo(out);
                                } else {
                                    source.writeTo(out);
                                    out.write('>');
                                }
                            }
                            case HREF -> {
                                out.write('"');
                                source.writeTo(out);
                                out.write('"');
                            }
                            case IMPORT -> {
                                var localStyleFile = localNameFor(outFile, source.toString(), false);
                                new TS_FileHtmlArchiveByteStreamBuilder().append('\"').appendUTF8(localStyleFile.getName()).appendUTF8("\")").writeTo(out);
                                bundle(remoteName(inputStr, source.toString()), localStyleFile, true);
                            }
                            case URL, SRC -> {
                                if (source.startsWithASCII("#")) {
                                    out.write('"');
                                    source.writeTo(out);
                                    out.write('"');
                                } else if (source.startsWithASCII("data:")) {
                                    var mimeType = new String[1];
                                    source = source.fromDataUri(mimeType);
                                    var localFileName = localNameFor(outFile, "resource-0" + fileExtension(mimeType[0]),
                                            false);
                                    source.writeTo(localFileName.toPath());
                                    new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("\"" + localFileName.getName() + "\"").writeTo(out);
                                } else if (source.startsWithASCII("omitted-svg-font")) {
                                    // Do nothing
                                } else {
                                    var sourceFile = remoteName(inputStr, source.toString());
                                    var localFileName = localNameFor(outFile, source.toString(), false);
                                    TS_FileHtmlArchiveByteStreamBuilder.forUrlOrFile(sourceFile).writeTo(localFileName.toPath());
                                    new TS_FileHtmlArchiveByteStreamBuilder().appendUTF8("\"" + localFileName.getName() + "\"").writeTo(out);
                                }
                                if (linkType == LinkType.URL) {
                                    out.write(')');
                                }
                            }
                            default -> {
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Prints an error message and quits
     */
    public static void error(CharSequence message) {
        System.err.println(message);
        System.exit(-1);
    }

    /**
     * Prints a warning message
     */
    public static void warn(CharSequence message) {
        System.err.println(message);
    }

    /**
     * Prints the help and exits
     */
    public static void helpAndExit() {
        error("""
              HTMLArchiver (BUNDLE|INLINE) <source> <target>
              
              Creates an archive of an HTML file or a Website (given by <source>), in one of the following ways:
              * BUNDLE: Copies the source and all referenced resources to a directory. <target> has to be a virgin directory.
              * INLINE: Creates a single HTML file from the source, which contains all resources. <target> has to be a non-existing file.
              
              See https://suchanek.name/programs/HTMLArchiver
              """);
    }

    /**
     * The HTMLArchiver is a Java tool that you can download on your computer.
     * Once downloaded, open a terminal and type java -jar HTMLArchiver.jar
     * INLINE (Web page or source file name) (target file) This will convert the
     * Web page or the source file into a single file, by inlining all resources
     * as Data URIs. Alternatively, you can use
     *
     * java -jar HTMLArchiver.jar BUNDLE (Web page or source file name) (target
     * folder) This will copy the Web page or source file and all resources to
     * the target folder, and adjust the references in the source accordingly.
     */
    public static void main(final String[] args0) {
//        args = new String[]{"BUNDLE", "www.mebosa.com", "D:\\me\\Documents\\ProgsCodes\\Maven\\MHTCreator\\"};
        var args = new String[]{"INLINE", "http://mebosa.com"};
        TGS_FuncMTCUtils.run(() -> {
            System.out.println("Hello");

            if (args.length != 3 && args.length != 2) {
                helpAndExit();
            }
            var source = args[1];
            File targetFile;
            if (args.length == 3) {
                targetFile = new File(args[2]);
            } else if (args[0].equals("INLINE") && !TGS_UrlUtils.isValidUrl(TGS_Url.of(source))) {
                var sourceFile = new File(source);
                var fileName = sourceFile.getName();
                var pos = fileName.lastIndexOf('.');
                if (pos != -1) {
                    fileName = fileName.substring(0, pos);
                }
                fileName += "-inlined.html";
                targetFile = new File(sourceFile.getParentFile(), fileName);
            } else {
                helpAndExit();
                return;
            }
            switch (args[0]) {
                case "BUNDLE" -> {
                    if (!targetFile.isDirectory()) {
                        error("Target directory does not exist " + targetFile);
                    }
                    targetFile = new File(targetFile, "index.html");
                    if (targetFile.exists()) {
                        error("File already exists: " + targetFile);
                    }
                    bundle(source, targetFile, false);
                }
                case "INLINE" -> {
                    if (targetFile.exists()) {
                        error("File already exists: " + targetFile);
                    }
                    try (var out = new FileOutputStream(targetFile)) {
                        inline(source, out, false, null);
                    }
                }
                case "INLINE+" -> {
                    if (!targetFile.isDirectory()) {
                        error("Directory does not exist " + targetFile);
                    }
                    try (var out = new FileOutputStream(new File(targetFile, "index.html"))) {
                        inline(source, out, false, new File(targetFile, "resources.html"));
                    }
                }
                default ->
                    helpAndExit();
            }
        });
    }

}
