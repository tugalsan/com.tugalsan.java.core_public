package com.tugalsan.java.core.gui.client.browser;

public class TGC_BrowserCalenderUtils {

    private TGC_BrowserCalenderUtils() {

    }

    public static native String calender_date() /*-{
        return "" + new Date();
    }-*/;

    public static native String calender_year() /*-{
        return "" + (new Date()).getFullYear();
    }-*/;

    public static native String calender_month() /*-{
        return "" + (new Date()).getMonth();
    }-*/;

    public static native String calender_date_of_month() /*-{
        return "" + (new Date()).getDate();
    }-*/;

    public static native String calender_day_of_week() /*-{
        return "" + (new Date()).getDay();
    }-*/;

    public static native String calender_hours() /*-{
        return "" + (new Date()).getHours();
    }-*/;

    public static native String calender_minutes() /*-{
        return "" + (new Date()).getMinutes();
    }-*/;

    public static native String calender_seconds() /*-{
        return "" + (new Date()).getSeconds();
    }-*/;

    public static native String calender_timezone() /*-{
        return "" + (new Date()).getTimezoneOffset()/-60;
    }-*/;
}
