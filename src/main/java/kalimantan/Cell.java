package kalimantan;

public final class Cell<T> implements Seq<T>{
    private final T val;
    private final Cell<T> next;

    public Cell(T val) {
        this.next = null;
        this.val = val;
    }

    private Cell(T val, Cell<T> next) {
        this.next = next;
        this.val = val;
    }

    public Cell<T> next() {
        return this.next;
    }

    public T getVal() {
        return this.val;
    }
}
