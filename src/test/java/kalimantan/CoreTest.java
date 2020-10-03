package kalimantan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static kalimantan.Core.*;

class CoreTests {

    @Test
    @DisplayName("Empty cells are equal")
    void testEmptyCellEqual() {
        assertEquals(cons(null, null), cons(null, null));
    }

    @Test
    @DisplayName("Cells with equal content are equal")
    void testEqualCells() {
        assertEquals(cons(1, null), cons(1, null));
    }

    @Test
    @DisplayName("Lists with equal contents are equal")
    void testEqualLists() {
        assertEquals(list(1, 2, 3), list(1, 2, 3));
    }

    @Test
    @DisplayName("Cells with non equal content are not equal")
    void testNotEqualCells() {
        assertNotEquals(cons(1, null), cons(2, null));
    }

    @Test
    @DisplayName("Lists with non equal content are not equal")
    void testNotEqualLists() {
        assertNotEquals(list(1, 2, 3), list(1, 2, 4));
    }


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

    @Test
    @DisplayName("rest on the final element of the seq returns an empty seq")
    void test3() {
        var seq = cons(1, null);
        var b = rest(seq);
        assertNull(first(b));
    }

    @Test
    @DisplayName("list returns a seq with all the arguments")
    void test4() {
        var seq = list(1, 2, 3, 4, 5);
        assertEquals(1, first(seq), "First element is 1");
        assertEquals(2, first(rest(seq)), "Second element is 2");
        assertEquals(3, first(rest(rest(seq))), "Third element is 3");
        assertEquals(4, first(rest(rest(rest(seq)))), "Fourth element is 4");
        assertEquals(5, first(rest(rest(rest(rest(seq))))), "Fifth element is 5");
        assertEquals(null, first(rest(rest(rest(rest(rest(rest(seq))))))), "Sixth element is null");
    }

    @Test
    @DisplayName("reducing collection of integers with a sum function")
    void test5() {
        assertEquals(15, reduce((Integer acc, Integer c) -> acc + c, 0, list(1, 2, 3, 4, 5)));
    }
}
