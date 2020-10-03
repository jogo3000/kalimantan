package kalimantan;


public interface Seq<T> {
    Cell<T> next();
    T getVal();
}
