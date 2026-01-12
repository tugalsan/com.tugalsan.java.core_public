package com.tugalsan.java.core.tree.client;

import com.tugalsan.java.core.list.client.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.*;

public class TGS_TreeBranch<A, B> extends TGS_TreeAbstract<A, B> implements Serializable {

    public TGS_TreeBranch() {//DTO
        this.childeren = null;
    }

    public TGS_TreeBranch(A id) {
        this(id, TGS_ListUtils.of());
    }

    public TGS_TreeBranch(A id, List<TGS_TreeAbstract<A, B>> childeren) {
        super(id);
        this.childeren = childeren;
    }
    final public List<TGS_TreeAbstract<A, B>> childeren;

    public static <A, B> TGS_TreeBranch<A, B> of(A id) {
        return new TGS_TreeBranch(id);
    }

    public static <A, B> TGS_TreeBranch<A, B> of(A id, List<TGS_TreeAbstract<A, B>> childeren) {
        return new TGS_TreeBranch(id, childeren);
    }

    public TGS_TreeBranch<A, B> add(TGS_TreeAbstract<A, B> child) {
        childeren.add(child);
        return this;
    }

    @Override
    public String toString() {
        return toString(0);
    }

    @Override
    public String toString(int indent) {
        var sb = new StringBuilder();
        IntStream.range(0, indent).forEach(i -> sb.append(" "));
        sb.append(TGS_TreeBranch.class.getSimpleName());
        sb.append(" -> id: ");
        sb.append(id);
        sb.append(" -> cc: ");
        sb.append(childeren.size());
        sb.append("\n");
        childeren.forEach(child -> sb.append(child.toString(indent + 1)));
        return sb.toString();
    }
}
