package com.tugalsan.java.core.url.server;

import module com.tugalsan.java.core.list;
import module com.tugalsan.java.core.url;
import java.nio.file.*;
import java.util.*;

public class TS_UrlFile {

    public TS_UrlFile(Path baseFolder, TGS_UrlBuilderDirectory baseUrl, CharSequence fileName, CharSequence... subPaths) {
        this(baseFolder, baseUrl, fileName, TGS_ListUtils.of(subPaths));
    }

    public TS_UrlFile(Path basePath, TGS_UrlBuilderDirectory baseUrl, CharSequence fileName, List<CharSequence> subPaths) {
        this.baseFolder = basePath;
        this.baseUrl = baseUrl;
        this.fileName = fileName;
        this.subPaths = subPaths;
    }
    public Path baseFolder;
    public TGS_UrlBuilderDirectory baseUrl;
    public CharSequence fileName;
    public List<CharSequence> subPaths;

    public Path toFile() {
        var file = baseFolder;
        for (var subPath : subPaths) {
            file = file.resolve(subPath.toString());
        }
        return file.resolve(fileName.toString());
    }

    public TGS_UrlBuilderFileOrServlet toUrl() {
        var url = baseUrl;
        for (var subPath : subPaths) {
            url = url.directory(subPath);
        }
        return url.fileOrServlet(fileName);
    }
}
