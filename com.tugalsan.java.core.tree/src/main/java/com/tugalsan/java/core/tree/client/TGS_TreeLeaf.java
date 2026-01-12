package com.tugalsan.java.core.tree.client;

import java.io.Serializable;
import java.util.stream.*;

public class TGS_TreeLeaf<A, B> extends TGS_TreeAbstract<A, B> implements Serializable {

    public TGS_TreeLeaf() {//DTO
    }

    public TGS_TreeLeaf(A id, B value) {
        super(id);
        this.value = value;
    }
    public B value;

    public static <A, B> TGS_TreeLeaf<A, B> of(A id, B value) {
        return new TGS_TreeLeaf(id, value);
    }

    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public String toString(int indent) {
        var sb = new StringBuilder();
        IntStream.range(0, indent).forEach(i -> sb.append(" "));
        sb.append(TGS_TreeLeaf.class.getSimpleName());
        sb.append(" -> id: ");
        sb.append(id);
        sb.append(" -> vl: ");
        sb.append(value);
        sb.append("\n");
        return sb.toString();
    }
}
