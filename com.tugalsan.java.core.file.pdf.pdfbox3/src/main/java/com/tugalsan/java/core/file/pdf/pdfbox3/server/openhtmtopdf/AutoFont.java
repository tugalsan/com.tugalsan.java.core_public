package com.tugalsan.java.core.file.pdf.pdfbox3.server.openhtmtopdf;

import module com.tugalsan.java.core.thread;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.union;
import module java.desktop;
import module com.tugalsan.java.core.file.pdf.pdfbox3.openhtmltopdf.pdfbox;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder.FontStyle;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.io.*;
import java.nio.file.attribute.*;
import java.util.stream.*;

//SOURCE: https://github.com/danfickle/openhtmltopdf/wiki/Fonts
/**
 * A tool for listing all the fonts in a directory.
 */
public class AutoFont {

    final private static TS_Log d = TS_Log.of(AutoFont.class);

    private AutoFont() {
    }

    /**
     * Returns a list of fonts in a given directory. NOTE: Should not be used
     * repeatedly as each font found is parsed to get the family name.
     *
     * @param validFileExtensions list of file extensions that are fonts -
     * usually Collections.singletonList("ttf")
     * @param recurse whether to look in sub-directories recursively
     * @param followLinks whether to follow symbolic links in the file system
     * @return a list of fonts.
     */
    public static List<CSSFont> findFontsInDirectory(
            Path directory, List<String> validFileExtensions, boolean recurse, boolean followLinks) throws IOException {

        FontFileProcessor processor = new FontFileProcessor(validFileExtensions);

        int maxDepth = recurse ? Integer.MAX_VALUE : 1;
        Set<FileVisitOption> options = followLinks ? EnumSet.of(FileVisitOption.FOLLOW_LINKS) : EnumSet.noneOf(FileVisitOption.class);

        Files.walkFileTree(directory, options, maxDepth, processor);

        return processor.getFontsAdded();
    }

    /**
     * Returns a list of fonts in a given directory. Recursively searches
     * directory and sub-directories for .ttf files. Follows symbolic links.
     * NOTE: Should not be used repeatedly as each font found is parsed to get
     * the family name.
     */
    final private static TS_ThreadSyncLockLimitedCall<CSSFont> runOnceAtTheSameTime = TS_ThreadSyncLockLimitedCall.of(new ReentrantLock());

    private static TGS_UnionExcuse<List<CSSFont>> __findFontsInDirectory(Path directory) {
        return TGS_FuncMTCUtils.call(() -> {
            return TGS_UnionExcuse.of(findFontsInDirectory(directory, Collections.singletonList("ttf"), true, true));
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public static TGS_UnionExcuse<List<CSSFont>> findFontsInDirectory(Path directory) {
        return runOnceAtTheSameTime.call(() -> {
            var u = __findFontsInDirectory(directory);
            if (u.isExcuse()) {
                TGS_FuncMTUUtils.thrw(u.excuse());
            }
            return u.value();
        });
    }

    /**
     * Get a string containing added font families (duplicates removed) in a
     * format suitable for the CSS font-family property.
     *
     * WARNING: Basic escaping, may not be robust to attack.
     */
    public static String toCSSEscapedFontFamily(List<CSSFont> fontsList) {
        return fontsList.stream()
                .map(fnt -> '\'' + fnt.familyCssEscaped() + '\'')
                .distinct()
                .collect(Collectors.joining(", "));
    }

    /**
     * Adds all fonts in the list to the builder.
     */
    public static void toBuilder(PdfRendererBuilder builder, List<CSSFont> fonts, TGS_FuncMTU_OutBool_In1<Path> filter) {
        fonts.stream().filter(font -> filter.validate(font.path)).forEachOrdered(font -> {
            System.out.format("Adding font to builder with path = '%s'\n", font.path.toString());
            builder.useFont(font.path.toFile(), font.family, font.weight, font.style, true);
        });
    }

    public static class CSSFont {

        public final Path path;
        public final String family;

        /**
         * WARNING: Heuristics are used to determine if a font is bold (700) or
         * normal (400) weight.
         */
        public final int weight;

        /**
         * WARNING: Heuristics are used to determine if a font is italic or
         * normal style.
         */
        public final FontStyle style;

        public CSSFont(Path path, String family, int weight, FontStyle style) {
            this.path = path;
            this.family = family;
            this.weight = weight;
            this.style = style;
        }

        /**
         * WARNING: Basic escaping, may not be robust to attack.
         */
        public String familyCssEscaped() {
            return this.family.replace("'", "\\'");
        }

        @Override
        public int hashCode() {
            return Objects.hash(path, family, weight, style);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (other == null
                    || other.getClass() != this.getClass()) {
                return false;
            }

            CSSFont b = (CSSFont) other;

            return Objects.equals(this.path, b.path)
                    && Objects.equals(this.family, b.family)
                    && this.weight == b.weight
                    && this.style == b.style;
        }
    }

    public static class FontFileProcessor extends SimpleFileVisitor<Path> {

        private final List<String> validFileExtensions;
        private final List<CSSFont> fontsAdded = new ArrayList<>();

        public FontFileProcessor(List<String> validFileExtensions) {
            this.validFileExtensions = new ArrayList<>(validFileExtensions);
        }

        public List<CSSFont> getFontsAdded() {
            return this.fontsAdded;
        }

        @Override
        public FileVisitResult visitFile(Path font, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile() && isValidFont(font)) {
                try {
                    Font f = Font.createFont(Font.TRUETYPE_FONT, font.toFile());

                    String family = f.getFamily();
                    // Short of parsing the font ourselves there doesn't seem to be a way
                    // of getting the font properties, so we use heuristics based on font name.
                    String name = f.getFontName(Locale.US).toLowerCase(Locale.US);
                    int weight = name.contains("bold") ? 700 : 400;
                    FontStyle style = name.contains("italic") ? FontStyle.ITALIC : FontStyle.NORMAL;

                    CSSFont fnt = new CSSFont(font, family, weight, style);

                    onValidFont(fnt);
                    if (fontsAdded.stream().noneMatch(_font -> _font.path.equals(fnt.path))) {
                        fontsAdded.add(fnt);
                    }
                } catch (FontFormatException ffe) {
                    onInvalidFont(font, ffe);
                }
            }

            return FileVisitResult.CONTINUE;
        }

        protected void onValidFont(CSSFont font) {
            d.cr("onValidFont", "Valid font found with path = '%s', name = '%s', weight = %d, style = %s%n", font.path, font.family, font.weight, font.style.name());
        }

        protected void onInvalidFont(Path font, FontFormatException ffe) {
            d.ce("onValidFont", "Ignoring font file with invalid font format: " + font);
            d.ce("onValidFont", "Exception details: ");
            ffe.printStackTrace();
        }

        protected boolean isValidFont(Path font) {
            return this.validFileExtensions.stream()
                    .anyMatch(ext -> font.toString().endsWith(ext));
        }
    }
}
