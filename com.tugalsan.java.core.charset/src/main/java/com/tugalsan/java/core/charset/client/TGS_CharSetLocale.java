package com.tugalsan.java.core.charset.client;

import com.google.gwt.core.shared.GwtIncompatible;
import com.google.gwt.i18n.client.LocaleInfo;
import java.util.Locale;
//import java.util.Locale;

public class TGS_CharSetLocale {

    public static boolean FORCE_DEFAULT_TO_TURKISH_ON_INIT = true;

    //UserHappy
    public static Common cmn() {
        return memoryHappy().cmn;
    }

    //MemoryHappy
    private static TGS_CharSetLocale memoryHappy() {
        if (holder == null) {
            holder = new TGS_CharSetLocale();
        }
        return holder;
    }
    private static volatile TGS_CharSetLocale holder = null;

    private TGS_CharSetLocale() {

    }
    final public Common cmn = new Common();

    //Common
    public static class Common extends CommonGwt {

        @Override
        @GwtIncompatible
        public TGS_CharSetLocaleTypes currentTypeGet() {
            if (currentType == null) {
                if (FORCE_DEFAULT_TO_TURKISH_ON_INIT) {
                    currentTypeSet(TGS_CharSetLocaleTypes.TURKISH);
                } else {
                    var tmp = Locale.getDefault().getLanguage();
                    if (tmp.contains("tr")) {
                        currentType = TGS_CharSetLocaleTypes.TURKISH;
                    } else {
                        currentType = TGS_CharSetLocaleTypes.ENGLISH;
                    }
                }
            }
            return currentType;
        }

        @Override
        @GwtIncompatible
        public void currentTypeSet(TGS_CharSetLocaleTypes currentType) {
            super.currentTypeSet(currentType);
            if (currentType == TGS_CharSetLocaleTypes.TURKISH) {
                Locale.setDefault(Locale.of("tr", "TR"));
                return;
            }
            Locale.setDefault(Locale.ENGLISH);
        }
    }

    //CommonGwt
    public static class CommonGwt {

        public TGS_CharSetLocaleTypes currentTypeGet() {
            if (currentType == null) {
                if (FORCE_DEFAULT_TO_TURKISH_ON_INIT) {
                    currentTypeSet(TGS_CharSetLocaleTypes.TURKISH);
                } else {
                    var tmp = LocaleInfo.getCurrentLocale().getLocaleName();
                    if (tmp.contains("tr")) {
                        currentType = TGS_CharSetLocaleTypes.TURKISH;
                    } else {
                        currentType = TGS_CharSetLocaleTypes.ENGLISH;
                    }
                }
            }
            return currentType;
        }
        protected TGS_CharSetLocaleTypes currentType = null;

        public void currentTypeSet(TGS_CharSetLocaleTypes currentType) {
            this.currentType = currentType;
        }
    }
}
