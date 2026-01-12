package com.tugalsan.java.core.gwt.jdk.client;

import com.google.gwt.core.shared.GwtIncompatible;

public class TGS_GwtJdk {
    
    //UserHappy
    public static Common cmn() {
        return memoryHappy().cmn;
    }

    public static OnlyJreHappy jre() {
        return memoryHappy().jre;
    }

    public static OnlyGwtHappy gwt() {
        return memoryHappy().gwt;
    }

    //MemoryHappy
    private static TGS_GwtJdk memoryHappy() {
        if (holder == null) {
            holder = new TGS_GwtJdk();
        }
        return holder;
    }
    private static volatile TGS_GwtJdk holder = null;

    private TGS_GwtJdk() {

    }
    final private Common cmn = new Common();
    final private OnlyJreHappy jre = new OnlyJreHappy();
    final private OnlyGwtHappy gwt = new OnlyGwtHappy();

    //OnlyGwtHappy
    public static class OnlyGwtHappy {

        public static native void println(CharSequence text) /*-{console.log(String(text));}-*/;
    }

    //OnlyJreHappy
    public static class OnlyJreHappy {

        @GwtIncompatible
        public static void println(CharSequence text) {
            System.out.println(text);
        }
    }

    //Common
    public static class Common extends CommonGwt {

        @Override
        @GwtIncompatible
        public void println(CharSequence name) {
            OnlyJreHappy.println(name);
        }
    }

    //CommonGwt
    public static class CommonGwt {

        public void println(CharSequence name) {
            OnlyGwtHappy.println(name);
        }
    }
}
