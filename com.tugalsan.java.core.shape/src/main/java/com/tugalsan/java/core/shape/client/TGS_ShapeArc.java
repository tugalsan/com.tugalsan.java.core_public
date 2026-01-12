package com.tugalsan.java.core.shape.client;

public class TGS_ShapeArc<T, Y, Z> {

    public T x;
    public T y;
    public Y radius;
    public Z angleInDegrees_start;
    public Z angleInDegrees_end;

    private TGS_ShapeArc(TGS_ShapeLocation<T> position, Y radius, TGS_ShapeLocation<Z> angleInDegrees) {
        this(position.x, position.y, radius, angleInDegrees.x, angleInDegrees.y);
    }

    public static <T, Y, Z> TGS_ShapeArc<T, Y, Z> of(TGS_ShapeLocation<T> position, Y radius, TGS_ShapeLocation<Z> angleInDegrees) {
        return new TGS_ShapeArc(position, radius, angleInDegrees);
    }

    @Override
    public String toString() {
        return TGS_ShapeArc.class.getSimpleName() + "{x/y/radius/angleInDegrees_start/angleInDegrees_end: " + x + "/" + y + "/" + radius + "/" + angleInDegrees_start + "/" + angleInDegrees_end + "}";
    }

    public TGS_ShapeArc(T x, T y, Y radius, Z angleInDegrees_start, Z angleInDegrees_end) {
        set(x, y, radius, angleInDegrees_start, angleInDegrees_end);
    }

    final public void set(T x, T y, Y radius, Z angleInDegrees_start, Z angleInDegrees_end) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.angleInDegrees_start = angleInDegrees_start;
        this.angleInDegrees_end = angleInDegrees_end;
    }
}
