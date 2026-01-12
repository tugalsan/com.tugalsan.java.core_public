package com.tugalsan.java.core.file.json.server;

import module com.tugalsan.java.core.file.txt;
import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.union;
import module com.fasterxml.jackson.annotation;
import module  com.fasterxml.jackson.databind;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

public class TS_FileJsonUtils {

    private TS_FileJsonUtils() {

    }

    public static <T> T[] toArray(Path filePath, Class<T[]> innerClassNameArray_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(filePath.toFile(), innerClassNameArray_dot_class);
        });
    }

    public static <T> T[] toArray(CharSequence jsonString, Class<T[]> innerClassNameArray_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString.toString(), innerClassNameArray_dot_class);
        });
    }

    public static <T> List<T> toList(Path filePath, Class<T> innerClassName_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(filePath.toFile(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, innerClassName_dot_class));
        });
    }

    public static <T> List<T> toList(CharSequence jsonString, Class<T> innerClassName_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(jsonString.toString(), mapper.getTypeFactory().constructCollectionType(ArrayList.class, innerClassName_dot_class));
        });
    }

    //ATTENTION ALL POJO FIELDS MUST BE PUBLIC. 
    //OR U WILL GET SERIALIZATION EXCEPTION
    //a = objectMapper.readValue(jsonString, Aligel.class);
    public static <T> TGS_UnionExcuse<T> toObject(CharSequence jsonString, Class<T> className_dot_class) {
        return TGS_FuncMTCUtils.call(() -> {
            var obj = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .readValue(jsonString.toString(), className_dot_class);
            return TGS_UnionExcuse.of(obj);
        }, e -> TGS_UnionExcuse.ofExcuse(e));
    }

    public static String toJSON(Object o, boolean pretty) {
        return TGS_FuncMTCUtils.call(() -> {
            var om = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            if (pretty) {
                om.enable(SerializationFeature.INDENT_OUTPUT);
            }
            return om.writeValueAsString(o);
        });
    }

    public static Path toFile(CharSequence sourceJSON, Path destFile, boolean withUTF8BOM, boolean windowsCompatable) {
        return TS_FileTxtUtils.toFile(sourceJSON, destFile, false, StandardCharsets.UTF_8, withUTF8BOM, windowsCompatable);
    }

    public static Path toFile(Object o, boolean pretty, Path destFile, boolean withUTF8BOM, boolean windowsCompatable) {
        return TS_FileTxtUtils.toFile(toJSON(o, pretty), destFile, false, StandardCharsets.UTF_8, withUTF8BOM, windowsCompatable);
    }
}
