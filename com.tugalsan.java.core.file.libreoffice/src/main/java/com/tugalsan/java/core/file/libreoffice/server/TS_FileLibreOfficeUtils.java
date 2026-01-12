package com.tugalsan.java.core.file.libreoffice.server;

import module com.tugalsan.java.core.function;
import module com.tugalsan.java.core.union;
import java.nio.file.Path;
import java.util.List;
import org.jodconverter.local.*;
import org.jodconverter.local.office.*;
import org.jodconverter.core.office.*;

import module com.tugalsan.java.core.log;

public class TS_FileLibreOfficeUtils {

    private TS_FileLibreOfficeUtils() {

    }

    final private static TS_Log d = TS_Log.of(TS_FileLibreOfficeUtils.class);

    public static TGS_UnionExcuseVoid convert(Path folderLibreOffice, Path input, Path output) {
        return convert(folderLibreOffice, List.of(input), List.of(output));
    }

    //EXAMPLE folderLibreOffice: C:\\Program Files\\LibreOffice
    public static TGS_UnionExcuseVoid convert(Path folderLibreOffice, List<Path> input, List<Path> output) {
        var om = LocalOfficeManager.builder().install().officeHome(folderLibreOffice.toAbsolutePath().toString()).build();
        return TGS_FuncMTCUtils.call(() -> {
            om.start();//start and connect on port 2002
            for (var i = 0; i < Math.min(input.size(), output.size()); i++) {
                JodConverter.convert(input.get(i).toFile()).to(output.get(i).toFile()).execute();
            }
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> TGS_UnionExcuseVoid.ofExcuse(e), () -> OfficeUtils.stopQuietly(om));
    }
}
