package com.tugalsan.java.core.charset.client;

import com.google.gwt.core.shared.GwtIncompatible;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class TGS_CharSet {

    //UserHappy
    public static Common cmn() {
        return memoryHappy().cmn;
    }

    public static OnlyJreHappy jre() {
        return memoryHappy().jre;
    }

    //MemoryHappy
    private static TGS_CharSet memoryHappy() {
        if (holder == null) {
            holder = new TGS_CharSet();
        }
        return holder;
    }
    private static volatile TGS_CharSet holder = null;

    private TGS_CharSet() {

    }
    final public Common cmn = new Common();
    final public OnlyJreHappy jre = new OnlyJreHappy();

    //OnlyJREHappy
    public static class OnlyJreHappy {

        @GwtIncompatible
        public boolean isASCIIPrintable(CharSequence text) {
            return text.codePoints().allMatch(c -> c > 31 && c < 127);
        }

        @GwtIncompatible
        public boolean isASCII(CharSequence text) {
            return StandardCharsets.US_ASCII.newEncoder().canEncode(text);
        }

        @GwtIncompatible
        public String makePrintable(CharSequence text) {
            if (text == null) {
                return "";
            }
            return text.toString().replaceAll("\\P{Print}", "");
        }

        @GwtIncompatible
        public boolean isPrintable_slow(CharSequence text) {
            return Objects.equals(text, makePrintable(text));
        }
    }

    //Common
    public static class Common extends CommonGwt {

        private Common() {
            super();
        }

    }

    //CommonGwt
    public static class CommonGwt {

        private CommonGwt() {

        }

        //    public static String ISO_TURKISH() {return "ISO_8859_9";}
        public String getCharset_IBM_TURKISH() {//USED BY TS_FilePdfItext.getFontInternal
            return "Cp857";
        }

        public Charset getCharset_ByName(CharSequence charsetName) {
            return Charset.availableCharsets().get(charsetName.toString());
        }

        public String to(CharSequence source, Charset sourceCharset, Charset destCharset) {
            return to(source, sourceCharset, destCharset, false);
        }

        public String to(CharSequence source, Charset sourceCharset, Charset destCharset, boolean removeHiddenLetters) {
            if (removeHiddenLetters) {
                source = removeHidden(source);
            }
            return new String(source.toString().getBytes(sourceCharset), destCharset);
        }

        public String removeHidden(CharSequence text) {
            if (text == null) {
                return null;
            }
            return text.toString().replace("\n", "").replace("\r", "").replaceAll("\\p{C}", "?");
        }

        public String UTF8_INCOMPLETE() {
            return "░"; // U+2591 Unicode Character
        }

        public String UTF8_COMPLETE() {
            return "█"; // // U+2588 Unicode Character
        }

        final public static String UTF8 = "UTF-8";//TS_SCharSetWebFilterUTF8 needs is static
//    public  String UTF8_StandardCharsets() {return StandardCharsets.UTF_8.name();}//STATIC NEEDED FOR TGS_CharSetWebFilter
//    public  String UTF8_OS (){return  Charset.defaultCharset().name();}//GWT does not like u; check on 2.10 version again!

        //    public  String UTF8_DISTINCT (){return  "◐";}
//    public  String UTF8_STAR (){return  "☆";}
//    public  String UTF8_EMAILSEND (){return  "📩";}
//    public  String UTF8_BROOM (){return  "🧹";}
//    public  String UTF8_COFFEE (){return  "⛾";}
//    public  String UTF8_PLUS_CIRCLE (){return  "⨁";}
//    public  String UTF8_FOLDER_CLOSED (){return  "📁";}//🗁
//    public  String UTF8_FOLDER_OPENED (){return  "📂";}//🗀
//    public  String UTF8_PAPER_BOTH (){return  "🖻";}
//    public  String UTF8_PAPER_PICTURE (){return  "🖺";}
//    public  String UTF8_PAPER_EMPTY (){return  "🗋";}
        public String UTF8_HAND_VICTORY() {
            return "✌";
        }
//    public  String UTF8_APPLE (){return  "🍎";}
//    public  String UTF8_MOVIE (){return  "🎥";}
//    public  String UTF8_BOOK (){return  "📚";}
//    public  String UTF8_BIRD (){return  "🐦";}
//    public  String UTF8_MUSIC (){return  "🎵";}
//    public  String UTF8_ZERO (){return  "∅";}
//    public  String UTF8_WATCH (){return  "⏰";}//⌚
//    public  String UTF8_CALENDER (){return  "📅";}
//    public  String UTF8_RUN (){return  "🏃";}
//    public  String UTF8_PHONELAND (){return  "☏";}//☎
//    public  String UTF8_EMAIL (){return  "✉";}
//    public  String UTF8_ARROWRIGHTPLUS (){return  "⥅";}
//    public  String UTF8_SAVE (){return  "💾";}
//    public  String UTF8_SELECT_TRUE (){return  "☑";}
//    public  String UTF8_SELECT_FALSE (){return  "☐";}
//    public  String UTF8_FLAGWHITE (){return  "⚐";}
//    public  String UTF8_ARROWUP (){return  "🠕";}
//    public  String UTF8_ARROWDOWN (){return  "🠗";}
//    public  String UTF8_QUESTION (){return  "❔";}
//    public  String UTF8_WARNING (){return  "❕";}
//    public  String UTF8_PRINT (){return  "⎙";}
//    public  String UTF8_PC (){return  "💻";}
//    public  String UTF8_FACTORY (){return  "🏭";}
//    public  String UTF8_TRASHBIN (){return  "🗑";}
//    public  String UTF8_PENCIL (){return  "🖉";}
//    public  String UTF8_PAGERIGHT (){return  "⎘";}
//    public  String UTF8_PAGELEFT (){return  "⎗";}
//    public  String UTF8_PAGESTART (){return  "⭶";}//⤓⭰
//    public  String UTF8_PAGEEND (){return  "️⭸";}//⤒⇥
//    public  String UTF8_SPREADSHEET (){return  "📋";}
//    public  String UTF8_INPUTNUMBERS (){return  "🔢";}
//    public  String UTF8_INPUTCHARSMALL (){return  " 🔡";}
//    public  String UTF8_INPUTCHARBIG (){return  "🔠";}
//    public  String UTF8_FAVORITE (){return  "⌘";}
//    public  String UTF8_PROCESSEMPTY (){return  "▱";}
//    public  String UTF8_PROCESSFULL (){return  "▰";}
//    public  String UTF8_KEY (){return  "🔑";}
//    public  String UTF8_DOOR (){return  "🚪";}
//    public  String UTF8_NEWS (){return  "📰";}
//    public  String UTF8_NEWSROLLED (){return  "🗞";}
//    public  String UTF8_PERSONINFO (){return  "💁";}
//    public  String UTF8_CLOSE (){return  "❎";}//☒
//    public  String UTF8_REFRESH (){return  "⟳";}
//    public  String UTF8_REPORTS (){return  "📈";}
//    public  String UTF8_FILE_CABINET (){return  "🗄";}
//    public  String UTF8_PAPER_TEXT (){return  "?";}
//    public  String UTF8_PAPER_DOUBLE (){return  "🗟";}
//    public  String UTF8_GLASSES (){return  "👓";}
//    public  String UTF8_CHECK (){return  "✔";}

        public String UTF8_PRAY() {
            return "\uE41D";
        }

        public String UTF8_HOURGLASS() {
            return "⌛";
        }

        public String UTF8_SORTDESC() {
            return "◣";
        }

        public String UTF8_SORTASC() {
            return "◢";
        }

        public String UTF8_CROSS() {
            return "✘";
        }

        public String UTF8_FILTER() {
            return "∀";
        }
    }
}
