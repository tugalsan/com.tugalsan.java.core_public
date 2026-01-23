package com.tugalsan.java.core.file.common.server;

import module com.tugalsan.java.core.url;
import module java.desktop;
import java.nio.file.*;
import java.util.*;

public abstract class TS_FileCommonAbstract {

    final public static boolean FILENAME_CHAR_SUPPORT_TURKISH = true;
    final public static boolean FILENAME_CHAR_SUPPORT_SPACE = true;

    public abstract String getSuperClassName();

    @Override
    public int hashCode() {
        var hash = 5;
        hash = 73 * hash + Objects.hashCode(getSuperClassName());
        hash = 73 * hash + Objects.hashCode(this.localFile);
        hash = 73 * hash + Objects.hashCode(this.remoteFile);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        var other = (TS_FileCommonAbstract) obj;
        if (!Objects.equals(this.localFile, other.localFile)) {
            return false;
        }
        return Objects.equals(this.remoteFile, other.remoteFile);
    }

    public TS_FileCommonAbstract(boolean enabled, Path localFile, TGS_Url remoteFile) {
        this.localFile = localFile;
        this.remoteFile = remoteFile;
        isEnabled = enabled;
        if (!enabled) {
            setClosed();
        }
    }

    public boolean isEnabled() {
        return isEnabled;
    }
    private boolean isEnabled = false;

    public boolean isClosed() {
        return isClosed;
    }
    private boolean isClosed = false;

    final protected void setClosed() {
        isClosed = true;
    }

    final public Path getLocalFileName() {
        return localFile;
    }

    final public void setLocalFileName(Path file) {
        localFile = file;
    }
    protected Path localFile;

    final public TGS_Url getRemoteFileName() {
        return remoteFile;
    }

    final public void setRemoteFileName(TGS_Url url) {
        remoteFile = url;
    }
    protected TGS_Url remoteFile;

    public abstract boolean saveFile(String errorSource);

    public abstract boolean createNewPage(int pageSizeAX, boolean landscape, Integer marginLeft, Integer marginRight, Integer marginTop, Integer marginBottom);

    public abstract boolean addImage(BufferedImage pstImage, Path pstImageLoc, boolean textWrap, int left0_center1_right2, long imageCounter);

    public abstract boolean beginTableCell(int rowSpan, int colSpan, Integer cellHeight);

    public abstract boolean endTableCell(int rotationInDegrees_0_90_180_270);

    public abstract boolean beginTable(int[] relColSizes);

    public abstract boolean endTable();

    public abstract boolean beginText(int allign_Left0_center1_right2_just3);

    public abstract boolean endText();

    public abstract boolean addText(String text);

    public boolean addTextPureCode(String text) {
        return addText(text);
    }

    public abstract boolean addLineBreak();

    public abstract boolean setFontStyle();

    public abstract boolean setFontHeight();

    public abstract boolean setFontColor();

    public void skipCloseFix() {

    }
}
