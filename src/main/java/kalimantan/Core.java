package kalimantan;

public interface Core {
    static <T> T first(Seq<T> seq) {
        return seq.getVal();
    }
}
