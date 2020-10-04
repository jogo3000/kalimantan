package kalimantan;


public interface Seq<T> {
    Seq<T> rest();
    T first();
    default boolean isEmpty() {
        return false;
    }
}

record EmptySeq<T>() implements Seq<T> {
    public T first() { return null; }
    public Seq<T> rest() { return this; }
    @Override
    public boolean isEmpty() { return true; }
}
