package kalimantan;


public interface Seq<T> {
    Seq<T> rest();
    T first();
}
