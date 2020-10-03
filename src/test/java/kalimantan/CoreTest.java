package kalimantan;

import java.util.function.*;

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
    @DisplayName("Empty lists are equal")
    void testEmptyListsEqual() {
        assertEquals(list(), list());
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

    @Test
    @DisplayName("mapping a Seq of integers with incrementing function returns a new Seq with the elements incremented")
    void testMap() {
        assertEquals(list(2, 3, 4), map((Integer c) -> c + 1, list(1, 2, 3)));
    }

    @Test
    @DisplayName("mapping an empty Seq returns an empty Seq")
    void testMapEmptySeq() {
        assertEquals(list(), map(Function.identity(), list()));
    }

    @Test
    @DisplayName("Filtering only positive values from a collection")
    void testFilterPositives() {
        assertEquals(list(1, 2, 3), filter((Integer i) -> i > 0, list(1, -1, -2, 2, 3, -5)));
    }

    @Test
    @DisplayName("last returns the last element of a seq")
    void testLast() {
        assertEquals("foo", last(list("bar", "baz", "foo")));
    }

    @Test
    @DisplayName("take 5 returns first five elements of a seq")
    void testTake5() {
        assertEquals(list(1, 2, 3, 4, 5), take(5, list(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }

    @Test
    @DisplayName("take 5 from a shorter seq returns the whole seq")
    void testTakeTooMuch() {
        assertEquals(list(1, 2, 3), take(5, list(1, 2, 3)));
    }

    @Test
    @DisplayName("count returns 5 for a seq of length 5")
    void testCount5() {
        assertEquals(5, count(list(1, 2, 3, 4, 5)));
    }
}
