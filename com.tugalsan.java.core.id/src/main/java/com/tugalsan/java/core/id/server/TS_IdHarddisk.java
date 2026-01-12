package com.tugalsan.java.core.id.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.stream;
import module com.tugalsan.java.core.union;
import java.nio.file.*;
import java.util.*;

public class TS_IdHarddisk {

    public static List<TGS_UnionExcuse<String>> get() {
        //TODO get: add LINUX implementaion: hdparm -i /dev/hda 
        return TGS_StreamUtils.toLst(
                TGS_StreamUtils.of(FileSystems.getDefault().getFileStores())
                        .map(item -> TGS_FuncMTCUtils.call(() -> {
                    return TGS_UnionExcuse.of(String.valueOf(item.getAttribute("volume:vsn")));
                }, e -> {
                    return TGS_UnionExcuse.ofExcuse(e);
                }))
        );
    }
}
