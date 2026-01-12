package com.tugalsan.java.core.color.client;

public class TGS_Color {

    public int r, g, b;

    public TGS_Color(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static TGS_Color of(int r, int g, int b) {
        return new TGS_Color(r, g, b);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.r;
        hash = 89 * hash + this.g;
        hash = 89 * hash + this.b;
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
        final TGS_Color other = (TGS_Color) obj;
        if (this.r != other.r) {
            return false;
        }
        if (this.g != other.g) {
            return false;
        }
        return this.b == other.b;
    }

    @Override
    public String toString() {
        return TGS_Color.class.getSimpleName() + "{" + "r=" + r + ", g=" + g + ", b=" + b + '}';
    }
}
