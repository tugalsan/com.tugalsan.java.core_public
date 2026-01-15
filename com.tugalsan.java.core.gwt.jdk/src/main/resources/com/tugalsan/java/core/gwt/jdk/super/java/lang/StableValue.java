package java.lang;

import com.tugalsan.java.core.gwt.jdk.client.TGC_StableSupplier;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;

//https://stackoverflow.com/questions/78271237/adding-standard-java-classes-that-are-missing-in-gwt
public class StableValue<T> {

    private volatile T value;

    public static <T> StableValue<T> of() {
        return new StableValue();
    }

    public boolean trySet(T contents) {
        if (value == null) {
            value = contents;
            return true;
        }
        return false;
    }

    public T orElse(T other) {
        if (value == null) {
            return other;
        }
        return value;
    }

    public T orElseSet(Supplier<? extends T> supplier) {
        if (value == null) {
            return supplier.get();
        }
        return value;
    }

    public T orElseThrow() {
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public boolean isSet() {
        return value != null;
    }

    public static <T> Supplier<T> supplier(Supplier<? extends T> underlying) {
        Objects.requireNonNull(underlying);
        return new TGC_StableSupplier(underlying);
    }

    public static <T, R> Function<T, R> function(Set<? extends T> inputs, Function<? super T, ? extends R> underlying) {
        throw new UnsupportedOperationException("GWT code not yet written StableValue.function");
    }

    public static <E> List<E> list(int size, IntFunction<? extends E> mapper) {
        throw new UnsupportedOperationException("GWT code not yet written StableValue.list");
    }

    public static <K, V> Map<K, V> map(Set<K> keys, Function<? super K, ? extends V> mapper) {
        throw new UnsupportedOperationException("GWT code not yet written StableValue.map");
    }
}
