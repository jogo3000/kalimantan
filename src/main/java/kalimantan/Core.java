package kalimantan;

record Cell<T> (T first, Seq<T> rest) implements Seq<T> {

}

public interface Core {

    static <T> Seq<T> cons(T val, Seq<T> rest) {
        return new Cell<T>(val, rest);
    }

    static <T> T first(Seq<T> seq) {
        return seq.first();
    }

    static <T> Seq<T> rest(Seq<T> seq) {
        return seq.rest();
    }
}
