package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import com.tugalsan.java.core.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import com.tugalsan.java.core.string.client.TGS_StringUtils;
import module java.desktop;
import module org.apache.pdfbox;//import module pdfbox.examples;
import module com.github.davidmoten.wordwrap;
import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

public class TS_FilePdfBox3UtilsFont {

    public static float getFontHeight(PDFont font, float fontSize) {
        return font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize;
    }

    public static float getLetterCount(CharSequence input) {
        return TGS_StringUtils.jre().countVisibleLetters(input);
    }

    public static List<String> wordWrap(CharSequence input, int maxLetterCount) {
        return TGS_StringUtils.jre().toList_ln(
                WordWrap
                        .from(input)
                        .maxWidth(maxLetterCount)
                        .newLine("\n")
                        //                .includeExtraWordChars("~")
                        //                .excludeExtraWordChars("_")
                        .insertHyphens(true)
                        .breakWords(true)
                        .stringWidth(TS_FilePdfBox3UtilsFont::getLetterCount)
                        .wrap()
        );
    }

    public static void setItalic(PDFont font) {
        setMatrix(font, TS_FilePdfBox3UtilsPageMatrix.toMatrixSkew(-15, 0));
    }

    public static void setMatrix(PDFont font, Matrix m) {
        IntStream.range(0, 3).parallel().forEach(ri -> {
            IntStream.range(0, 3).parallel().forEach(ci -> {
                font.getFontMatrix().setValue(ri, ci, m.getValue(ri, ci));
            });
        });
    }

    public static void setBold(PDPageContentStream pcs, PDFont font, float fontSize, Color color) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.setFont(font, fontSize);
            pcs.setLineWidth(fontSize / 10);
            pcs.setRenderingMode(RenderingMode.FILL_STROKE);
            pcs.setNonStrokingColor(color);
        });
    }

    public static void set(PDPageContentStream pcs, PDFont font, float fontSize) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.setFont(font, fontSize);
        });
    }

    //I DONNO
    public static PDType1Font of_FileType1(PDDocument doc, Path pathFont) {
        return TGS_FuncMTCUtils.call(() -> {
            try (var is = Files.newInputStream(pathFont)) {
                return new PDType1Font(doc, is);
            }
        });
    }

    //TTF
    public static PDType0Font of_FileType0(PDDocument doc, Path pathFont) {
        return TGS_FuncMTCUtils.call(() -> {
            return PDType0Font.load(doc, pathFont.toFile());
        });
    }

    public static PDType1Font of_COURIER() {
        return new PDType1Font(Standard14Fonts.FontName.COURIER);
    }

    public static PDType1Font of_COURIER_BOLD() {
        return new PDType1Font(Standard14Fonts.FontName.COURIER_BOLD);
    }

    public static PDType1Font of_COURIER_BOLD_OBLIQUE() {
        return new PDType1Font(Standard14Fonts.FontName.COURIER_BOLD_OBLIQUE);
    }

    public static PDType1Font of_HELVETICA() {
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA);
    }

    public static PDType1Font of_HELVETICA_BOLD() {
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
    }

    public static PDType1Font of_HELVETICA_BOLD_OBLIQUE() {
        return new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD_OBLIQUE);
    }

    public static PDType1Font of_TIMES_ROMAN() {
        return new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN);
    }

    public static PDType1Font of_TIMES_ITALIC() {
        return new PDType1Font(Standard14Fonts.FontName.TIMES_ITALIC);
    }

    public static PDType1Font of_TIMES_BOLD() {
        return new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD);
    }

    public static PDType1Font of_TIMES_BOLD_ITALIC() {
        return new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD_ITALIC);
    }

    public static PDType1Font of_SYMBOL() {
        return new PDType1Font(Standard14Fonts.FontName.SYMBOL);
    }

    public static PDType1Font of_ZAPF_DINGBATS() {
        return new PDType1Font(Standard14Fonts.FontName.ZAPF_DINGBATS);
    }

}
