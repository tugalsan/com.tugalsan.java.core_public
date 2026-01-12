package com.tugalsan.java.core.desktop.server;

import module com.tugalsan.java.core.charset;
import module com.tugalsan.java.core.union;
import module com.tugalsan.java.core.function;
import module java.desktop;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.filechooser.FileFilter;

public class TS_DesktopPathUtils {

    private TS_DesktopPathUtils() {

    }

    public static Path currentFolder() {
        return Path.of("").toAbsolutePath().normalize();
    }

    public static enum Type {
        DIRECTORIES_ONLY, FILES_ONLY, FILES_AND_DIRECTORIES
    }

    public static TGS_UnionExcuse<Path> chooseFiles(String title, Optional<Path> initFolder, String... acceptedFileTypes) {
        return choose(title, initFolder, Type.FILES_ONLY, acceptedFileTypes);
    }

    public static TGS_UnionExcuse<Path> chooseFileOrDirectory(String title, Optional<Path> initFolder) {
        return choose(title, initFolder, Type.FILES_AND_DIRECTORIES);
    }

    public static TGS_UnionExcuse<Path> chooseDirectory(String title, Optional<Path> initFolder) {
        return choose(title, initFolder, Type.DIRECTORIES_ONLY);
    }

    private static TGS_UnionExcuse<Path> choose(String title, Optional<Path> initFolder, Type type, String... acceptedFileTypes) {
        var c = new JFileChooser();
        c.setDialogTitle(title);
        c.setCurrentDirectory(initFolder.isEmpty() ? new File(".") : initFolder.get().toFile());
        switch (type) {
            case DIRECTORIES_ONLY ->
                c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            case FILES_ONLY ->
                c.setFileSelectionMode(JFileChooser.FILES_ONLY);
            default ->
                c.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        }
        if (acceptedFileTypes.length == 0) {
            c.setAcceptAllFileFilterUsed(false);
        } else {
            c.setFileFilter(new FileFilter() {

                @Override
                public String getDescription() {
                    return "👓";
                }

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    var filenameLowerCase = TGS_CharSetCast.current().toLowerCase(f.getName());
                    return Arrays.stream(acceptedFileTypes)
                            .map(ft -> TGS_CharSetCast.current().toLowerCase(ft))
                            .anyMatch(ft -> filenameLowerCase.endsWith("." + ft));
                }
            });
        }
        var choice = c.showOpenDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            return TGS_UnionExcuse.ofExcuse(TS_DesktopPathUtils.class.getSimpleName(), "choose", "choice!= JFileChooser.APPROVE_OPTION");
        }
        return TGS_UnionExcuse.of(c.getSelectedFile().toPath());
    }

    public static TGS_UnionExcuse<Path> save(String title, Optional<Path> initFolder) {
        var c = new JFileChooser();
        c.setDialogTitle(title);
        c.setCurrentDirectory(initFolder.isEmpty() ? new File(".") : initFolder.get().toFile());
        var choice = c.showSaveDialog(null);
        if (choice != JFileChooser.APPROVE_OPTION) {
            return TGS_UnionExcuse.ofExcuse(TS_DesktopPathUtils.class.getSimpleName(), "save", "choice!= JFileChooser.APPROVE_OPTION");
        }
        return TGS_UnionExcuse.of(c.getSelectedFile().toPath());
    }

    public static TGS_UnionExcuseVoid run(Path file) {
        return TGS_FuncMTCUtils.call(() -> {
            Desktop.getDesktop().open(file.toFile());
            return TGS_UnionExcuseVoid.ofVoid();
        }, e -> {
            return TGS_UnionExcuseVoid.ofExcuse(e);
        });
    }
}
