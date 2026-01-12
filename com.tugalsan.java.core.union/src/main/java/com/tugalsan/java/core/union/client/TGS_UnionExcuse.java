package com.tugalsan.java.core.union.client;

import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTUUtils;
import com.tugalsan.java.core.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_OutTyped_In1;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.*;

//public record TGS_UnionExcuse<T>(T value, Throwable excuse) {//GWT does not like record!
public class TGS_UnionExcuse<T> {

    private TGS_UnionExcuse(T value, Throwable excuse) {
        this.value = value;
        this.excuse = excuse;
    }

    public <R> TGS_UnionExcuse<R> map(Function<T, R> mapper) {
        if (value == null) {
            return TGS_UnionExcuse.of(mapper.apply(value));
        } else {
            return TGS_UnionExcuse.ofExcuse(excuse);
        }
    }

    public T value() {
        throwIfExcuse();
        return value;
    }
    private T value;

    @Deprecated //not safe, or just use value
    private <R> R throwIfExcuse() {
        if (excuse == null) {
            return null;
        }
        TGS_FuncMTUUtils.thrw(excuse);
        return null;
    }

    public Throwable excuse() {
        if (excuse == null) {
            throw new UnsupportedOperationException("union is a value");
        }
        return excuse;
    }
    private Throwable excuse;

    public static <T> TGS_UnionExcuse<T> ofExcuse(Supplier className, CharSequence funcName, Object excuse) {
        return ofExcuse(
                new RuntimeException(
                        "CLASS[" + className.get() + "].FUNC[" + funcName + "].EXCUSE: " + excuse
                )
        );
    }

    public static <T> TGS_UnionExcuse<T> ofExcuse(CharSequence className, CharSequence funcName, Object excuse) {
        return ofExcuse(
                new RuntimeException(
                        "CLASS[" + className + "].FUNC[" + funcName + "].EXCUSE: " + excuse
                )
        );
    }

    public <T> TGS_UnionExcuse<T> toExcuse() {
        if (excuse.getClass().getSimpleName().equals(InterruptedException.class.getSimpleName())) {
            return TGS_UnionExcuse.ofExcuse(excuse);
        }
        if (excuse.getClass().getSimpleName().equals(TimeoutException.class.getSimpleName())) {
            return TGS_UnionExcuse.ofExcuse(excuse);
        }
        return TGS_UnionExcuse.ofExcuse(TGS_UnionException.of(this));
    }

    public TGS_UnionExcuseVoid toExcuseVoid() {
        if (excuse.getClass().getSimpleName().equals(InterruptedException.class.getSimpleName())) {
            return TGS_UnionExcuseVoid.ofExcuse(excuse);
        }
        if (excuse.getClass().getSimpleName().equals(TimeoutException.class.getSimpleName())) {
            return TGS_UnionExcuseVoid.ofExcuse(excuse);
        }
        return TGS_UnionExcuseVoid.ofExcuse(TGS_UnionException.of(this));
    }

    public static <T> TGS_UnionExcuse<T> ofExcuse(Throwable excuse) {
        return new TGS_UnionExcuse(null, excuse);
    }

    public static <T> TGS_UnionExcuse<T> ofEmpty_NullPointerException() {
        return of(null);
    }

    public static <T> TGS_UnionExcuse<T> of(T value) {
        return value == null
                ? ofExcuse(new UnsupportedOperationException("value is not introduced"))
                : new TGS_UnionExcuse(value, null);
    }

    public boolean isExcuse() {
        return excuse != null;
    }

    public void ifPresent(Consumer<? super T> action) {
        if (value != null) {
            action.accept(value);
        }
    }

    public T orElse(TGS_FuncMTU_OutTyped_In1<T, Throwable> excuse) {
        if (value != null) {
            return value;
        }
        if (excuse == null) {
            return null;
        }
        return excuse.call(this.excuse);
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isExcuseTimeout() {
        return excuse != null && excuse instanceof TimeoutException;
    }

    public boolean isExcuseInterrupt() {
        return excuse != null && excuse instanceof InterruptedException;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.value);
        hash = 59 * hash + Objects.hashCode(this.excuse);
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
        final TGS_UnionExcuse<?> other = (TGS_UnionExcuse<?>) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return Objects.equals(this.excuse, other.excuse);
    }

    @Override
    public String
            toString() {
        if (isExcuse()) {
            return TGS_UnionExcuse.class
                    .getSimpleName() + "{excuse=" + excuse + '}';
        }
        return String.valueOf(value);
    }

}
