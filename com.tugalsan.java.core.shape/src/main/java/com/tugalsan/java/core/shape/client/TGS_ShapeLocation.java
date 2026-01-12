package com.tugalsan.java.core.shape.client;

public class TGS_ShapeLocation<T> {

    public T x;
    public T y;

    @Override
    public String toString() {
        return TGS_ShapeLocation.class.getSimpleName() + "{x/y: " + x + "/" + y + "}";
    }

    public TGS_ShapeLocation(T x, T y) {
        set(x, y);
    }

    private TGS_ShapeLocation(TGS_ShapeLocation<T> position) {
        sniffFrom(position);
    }

    public static <T> TGS_ShapeLocation<T> of(T x, T y) {
        return new TGS_ShapeLocation(x, y);
    }

    final public void sniffFrom(TGS_ShapeLocation<T> position) {
        this.x = position.x;
        this.y = position.y;
    }

    public TGS_ShapeLocation<T> cloneIt(TGS_ShapeLocation<T> position) {
        return of(position.x, position.y);
    }

    final public void set(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public static TGS_ShapeLocation<Double>[] ofPairedValues(double... pairedValues) {
        TGS_ShapeLocation<Double>[] locs = new TGS_ShapeLocation[pairedValues.length];
        var offset = 0;
        for (var i = 0; i < pairedValues.length; i += 2) {
            var loc = new TGS_ShapeLocation<Double>(pairedValues[i], pairedValues[i + 1]);
            locs[offset] = loc;
            offset++;
        }
        return locs;
    }

    public static TGS_ShapeLocation<Float>[] ofPairedValues(float... pairedValues) {
        TGS_ShapeLocation<Float>[] locs = new TGS_ShapeLocation[pairedValues.length];
        var offset = 0;
        for (var i = 0; i < pairedValues.length; i += 2) {
            var loc = new TGS_ShapeLocation<Float>(pairedValues[i], pairedValues[i + 1]);
            locs[offset] = loc;
            offset++;
        }
        return locs;
    }

    public static TGS_ShapeLocation<Integer>[] ofPairedValues(int... pairedValues) {
        TGS_ShapeLocation<Integer>[] locs = new TGS_ShapeLocation[pairedValues.length];
        var offset = 0;
        for (var i = 0; i < pairedValues.length; i += 2) {
            var loc = new TGS_ShapeLocation<Integer>(pairedValues[i], pairedValues[i + 1]);
            locs[offset] = loc;
            offset++;
        }
        return locs;
    }
}
