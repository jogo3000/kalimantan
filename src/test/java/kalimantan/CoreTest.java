package kalimantan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static kalimantan.Core.*;

class CoreTests {

    @Test
    @DisplayName("first returns the first element in a Seq")
    void test1() {
        var seq = cons(1, null);
        assertEquals(1, first(seq));
    }

    @Test
    @DisplayName("rest returns the rest of the seq")
    void test2() {
        var seq = cons(1, cons(2, null));
        var b = rest(seq);
        assertEquals(2, first(b));

    }
}
