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
        return new Cell<T>(val, (seq == null || isEmpty(seq)) ? null : seq);
    }

    static <T> T first(final Seq<T> seq) {
        return seq.first();
    }

    static <T> Seq<T> rest(final Seq<T> seq) {
        return seq.rest() != null ? seq.rest() : new EmptySeq();
    }

    static <T> T last(final Seq<T> s) {
        if (isEmpty(s)) {
            return null;
        }
        Seq<T> c = s;
        while (c.rest() != null) {
            c = rest(c);
        }
        return first(c);
    }

    static <T> Seq<T> list(final T... args) {
        var len = args != null ? args.length : 0;
        Seq<T> seq = null;
        var c = len - 1;
        while (c >= 0) {
            seq = cons(args[c], seq);
            c--;
        }
        return seq != null ? seq : new EmptySeq();
    }

    static <T, R> Seq<R> map(Function<T, R> fun, Seq<T> s) {
        if (isEmpty(s)) {
            return new EmptySeq();
        }
        return s.rest() != null ?
            // This will blow up the stack when `s` is large enough
            cons(fun.apply(first(s)), map(fun, rest(s)))
            : cons(fun.apply(first(s)), null) ;
    }

    static <T> Seq<T> filter(Predicate<T> pred, Seq<T> s) {
        if (isEmpty(s)) {
            return new EmptySeq();
        }

        if (pred.test(first(s))) {
            return cons(first(s), filter(pred, rest(s)));
        } else {
            return filter(pred, rest(s));
        }

    }

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

    static <T> Seq<T> take(final int i, final Seq<T> s) {
        return (i <= 0 || isEmpty(s)) ? new EmptySeq() : cons(first(s), take(i - 1, rest(s)));
    }

    static <T> int count(Seq<T> s) {
        return reduce((Integer acc, T v) -> acc + 1, 0, s);
    }
}
