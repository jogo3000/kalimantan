package kalimantan;
import java.util.function.*;

record Cell<T> (T first, Seq<T> rest) implements Seq<T> {}

record EmptySeq<T>() implements Seq<T> {
    public T first() { return null; }
    public Seq<T> rest() { return this; }
    @Override
    public boolean isEmpty() { return true; }
}

public interface Core {

    static <T> Seq<T> cons(final T val, final Seq<T> seq) {
        return new Cell<T>(val, seq);
    }

    static <T> T first(final Seq<T> seq) {
        return seq.first();
    }

    static <T> Seq<T> rest(final Seq<T> seq) {
        return seq.rest() != null ? seq.rest() : new EmptySeq();
    }

    static <T> Seq<T> list(final T... args) {
        var len = args != null ? args.length : 0;
        Seq<T> seq = null;
        var c = len - 1;
        while (c >= 0) {
            seq = cons(args[c], seq);
            c--;
        }
        return seq;
    }


    // TODO `filter`

    static <T> boolean isEmpty(Seq<T> s) {
        return s.isEmpty();
    }

    // TODO reduce with no initial value. How to, though? Reduce is
    // supposed to be able to return a different type than the Seq it
    // is consuming.  Are there union types in Java?

    static <R, T> R reduce(final BiFunction<R, T, R> fun, final R initial, final Seq<T> seq) {
        R acc = initial;
        Seq<T> c = seq;
        do {
            acc = fun.apply(acc, first(c));
            c = rest(c);
        } while (!isEmpty(c));
        return acc;
    }
}
