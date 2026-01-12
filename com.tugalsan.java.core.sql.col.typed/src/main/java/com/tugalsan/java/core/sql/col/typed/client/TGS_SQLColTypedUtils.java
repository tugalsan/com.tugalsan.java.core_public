package com.tugalsan.java.core.sql.col.typed.client;

import com.tugalsan.java.core.cast.client.TGS_CastUtils;

public class TGS_SQLColTypedUtils {

    public static String FAMILY_LNG() {
        return "LNG";
    }

    public static String FAMILY_STR() {
        return "STR";
    }

    public static String FAMILY_BYTES() {
        return "BYTES";
    }

    public static String TYPE_LNG() {
        return FAMILY_LNG();
    }

    public static String TYPE_LNGDATE() {
        return FAMILY_LNG().concat("DATE");
    }

    public static String TYPE_LNGTIME() {
        return FAMILY_LNG().concat("TIME");
    }

    
    public static String TYPE_LNGDOUBLE() {
        return FAMILY_LNG().concat("FLOAT");//DONT CHANGE THE VALUE 'FLOAT'! HARDCODED AS COLUMN NAME
    }

    public static String TYPE_LNGLINK() {
        return FAMILY_LNG().concat("LINK");
    }

    public static String TYPE_STR() {
        return FAMILY_STR();
    }

    public static String TYPE_STRLINK() {
        return FAMILY_STR().concat("LINK");
    }

    public static String TYPE_STRFILE() {
        return FAMILY_STR().concat("FILE");
    }

    public static String TYPE_BYTES() {
        return FAMILY_BYTES();
    }

    public static String TYPE_BYTESROW() {
        return FAMILY_BYTES().concat("ROW");
    }

    public static String TYPE_BYTESSTR() {
        return FAMILY_BYTES().concat("STR");
    }

    public static boolean familyLng(CharSequence columnName) {
        return columnName.toString().startsWith(FAMILY_LNG());
    }

    public static boolean familyLng(TGS_SQLColTyped col) {
        return familyLng(col.columnName);
    }

    public static boolean familyStr(CharSequence columnName) {
        return columnName.toString().startsWith(FAMILY_STR());
    }

    public static boolean familyStr(TGS_SQLColTyped col) {
        return familyStr(col.columnName);
    }

    public static boolean familyBytes(CharSequence columnName) {
        return columnName.toString().startsWith(FAMILY_BYTES());
    }

    public static boolean familyBytes(TGS_SQLColTyped col) {
        return familyBytes(col.columnName);
    }

    public static boolean groupLnk(TGS_SQLColTyped col) {
        return typeLngLnk(col) || typeStrLnk(col);
    }

    public static boolean groupLnk(CharSequence columnName) {
        return typeLngLnk(columnName) || typeStrLnk(columnName);
    }

    public static boolean typeLng(CharSequence columnName) {
        if (!familyLng(columnName)) {
            return false;
        }
        if (columnName.toString().startsWith(TYPE_LNG().concat("TAG"))) {//COMPATABILITY FIX
            return true;
        }
        var chr = String.valueOf(columnName.charAt(TYPE_LNG().length()));
        return chr.equals("_") || TGS_CastUtils.isInteger(chr);
    }

    public static boolean typeLng(TGS_SQLColTyped col) {
        return typeLng(col.columnName);
    }

    public static boolean typeLngDate(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_LNGDATE());
    }

    public static boolean typeLngDate(TGS_SQLColTyped col) {
        return typeLngDate(col.columnName);
    }

    public static boolean typeLngTime(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_LNGTIME());
    }

    public static boolean typeLngTime(TGS_SQLColTyped col) {
        return typeLngTime(col.columnName);
    }

    public static boolean typeLngDbl(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_LNGDOUBLE());
    }

    public static boolean typeLngDbl(TGS_SQLColTyped col) {
        return typeLngDbl(col.columnName);
    }

    public static boolean typeLngLnk(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_LNGLINK());
    }

    public static boolean typeLngLnk(TGS_SQLColTyped col) {
        return typeLngLnk(col.columnName);
    }

    public static boolean typeStr(CharSequence columnName) {
        if (!familyStr(columnName)) {
            return false;
        }
        var chr = String.valueOf(columnName.charAt(TYPE_STR().length()));
        return chr.equals("_") || TGS_CastUtils.isInteger(chr);
    }

    public static boolean typeStr(TGS_SQLColTyped col) {
        return typeStr(col.columnName);
    }

    public static boolean typeStrLnk(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_STRLINK());
    }

    public static boolean typeStrLnk(TGS_SQLColTyped col) {
        return typeStrLnk(col.columnName);
    }

    public static boolean typeStrFile(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_STRFILE());
    }

    public static boolean typeStrFile(TGS_SQLColTyped col) {
        return typeStrFile(col.columnName);
    }

    public static boolean typeBytes(CharSequence columnName) {
        if (!familyBytes(columnName)) {
            return false;
        }
        var chr = String.valueOf(columnName.charAt(TYPE_BYTES().length()));
        return chr.equals("_") || TGS_CastUtils.isInteger(chr);
    }

    public static boolean typeBytes(TGS_SQLColTyped col) {
        return typeBytes(col.columnName);
    }

    public static boolean typeBytesRow(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_BYTESROW());
    }

    public static boolean typeBytesRow(TGS_SQLColTyped col) {
        return typeBytesRow(col.columnName);
    }

    public static boolean typeBytesStr(CharSequence columnName) {
        return columnName.toString().startsWith(TYPE_BYTESSTR());
    }

    public static boolean typeBytesStr(TGS_SQLColTyped col) {
        return typeBytesStr(col.columnName);
    }
}
