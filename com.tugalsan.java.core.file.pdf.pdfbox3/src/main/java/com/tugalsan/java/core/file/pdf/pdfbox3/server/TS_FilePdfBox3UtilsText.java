package com.tugalsan.java.core.file.pdf.pdfbox3.server;

import module com.tugalsan.java.core.log;
import module com.tugalsan.java.core.string;
import module com.tugalsan.java.core.function;

import module org.apache.pdfbox;//import module pdfbox.examples;

public class TS_FilePdfBox3UtilsText {

    final private static TS_Log d = TS_Log.of(TS_FilePdfBox3UtilsText.class);

    public static void beginText(PDPageContentStream pcs) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.beginText();
        });
    }

    public static void endText(PDPageContentStream pcs) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.endText();
        });
    }

    public static void newLine_locationXY(PDPageContentStream pcs, float locX, float locY) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.setTextMatrix(TS_FilePdfBox3UtilsPageMatrix.toMatrixLocation(locX, locY));
        });
    }

    public static void newLine_translateXY(PDPageContentStream pcs, float translateX, float translateY) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.newLineAtOffset(translateX, translateY);
        });
    }

    public static void newLine_setLeading(PDPageContentStream pcs, float fontSize, float leadingAmout_from_1_to_2) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.setLeading(fontSize * leadingAmout_from_1_to_2);
        });
    }

    public static void newLine(PDPageContentStream pcs) {
        TGS_FuncMTCUtils.run(() -> {
            pcs.newLine();
        });
    }

    public static void addText(PDPageContentStream pcs, CharSequence text) {
        TGS_FuncMTCUtils.run(() -> {
            if (TGS_StringUtils.cmn().isNullOrEmpty(text)) {
                return;
            }
            pcs.showText(text.toString());
        });
    }

    public static String readTextDoc(PDDocument doc) {
        return TGS_FuncMTCUtils.call(() -> {
            var reader = new PDFTextStripper();
            return reader.getText(doc);
        });
    }

    public static String readTextPage(PDDocument doc, int pageIdx) {
        return TGS_FuncMTCUtils.call(() -> {
            var reader = new PDFTextStripper();
            reader.setStartPage(pageIdx + 1);
            reader.setEndPage(pageIdx + 1);
            return reader.getText(doc);
        });
    }

//    public static PDDocument replaceText(PDDocument document, String searchString, String replacement) throws IOException {
//        for (var page : document.getDocumentCatalog().getPages()) {
//            
//            PDFStreamParser parser = new PDFStreamParser(page);
//            parser.parse();
//            List tokens = parser.getTokens();
//            for (int j = 0; j < tokens.size(); j++) {
//                Object next = tokens.get(j);
//                if (next instanceof Operator) {
//                    Operator op = (Operator) next;
//                    //Tj and TJ are the two operators that display strings in a PDF
//                    if (op.getName().equals("Tj")) {
//                        // Tj takes one operator and that is the string to display so lets update that operator
//                        COSString previous = (COSString) tokens.get(j - 1);
//                        String string = previous.getString();
//                        string = string.replaceFirst(searchString, replacement);
//                        previous.setValue(string.getBytes());
//                    } else if (op.getName().equals("TJ")) {
//                        COSArray previous = (COSArray) tokens.get(j - 1);
//                        for (int k = 0; k < previous.size(); k++) {
//                            Object arrElement = previous.getObject(k);
//                            if (arrElement instanceof COSString) {
//                                COSString cosString = (COSString) arrElement;
//                                String string = cosString.getString();
//                                string = StringUtils.replaceOnce(string, searchString, replacement);
//                                cosString.setValue(string.getBytes());
//                            }
//                        }
//                    }
//                }
//            }
//            // now that the tokens are updated we will replace the page content stream.
//            PDStream updatedStream = new PDStream(document);
//            OutputStream out = updatedStream.createOutputStream();
//            ContentStreamWriter tokenWriter = new ContentStreamWriter(out);
//            tokenWriter.writeTokens(tokens);
//            page.setContents(updatedStream);
//            out.close();
//        }
//        return document;
//    }
}
