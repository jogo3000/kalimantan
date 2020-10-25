package kalimantan;

import java.util.function.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static kalimantan.Core.*;

public class CoreTest {

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
    @DisplayName("making a big list does not blow the stack")
    void testBigList() {
        var bigArray = new Integer[10_000];
        for (var i = 0; i < bigArray.length; i++)
            bigArray[i] = i;

        assertDoesNotThrow(() -> list(bigArray));
    }

    @Test
    @DisplayName("cons is immutable")
    void testConsImmutable() {
        var seq = list();
        var seq2 = list(1, 2);
        var seq3 = list(1, 2, 3);

        assertEquals(list(1), cons(1, seq));
        assertEquals(list(), seq);

        assertEquals(list(3, 1, 2), cons(3, seq2));
        assertEquals(list(1, 2), seq2);

        assertEquals(list(4, 1, 2, 3), cons(4, seq3));
        assertEquals(list(1, 2, 3), seq3);
    }

    @Test
    @DisplayName("reducing collection of integers with a sum function")
    void test5() {
        assertEquals(15, reduce((Integer acc, Integer c) -> acc + c, 0, list(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("reducing collection of integers with a sum function and no initial value")
    void testReduceWithoutInitial() {
        assertEquals(15, reduce((Integer acc, Integer c) -> acc + c, list(1, 2, 3, 4, 5)));
    }

    @Test
    @DisplayName("reducing empty collection without initial value returns null")
    void testReduceEmptyCollectionWithoutInitial() {
        assertNull(reduce((Integer acc, Integer c) -> acc + c, list()));
    }

    @Test
    @DisplayName("recuding collection with 1 element without initial value returns initial value")
    void testReduce1ElementCollectionWithoutInitial() {
        assertEquals(20, reduce((Integer acc, Integer c) -> acc + c, list(20)));
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
    @DisplayName("mapping a big collection does not blow the stack")
    void testMapHugeCollection() {
        var bigCollection = new Integer[10_000];
        for (int i = 0; i < bigCollection.length; i++)
            bigCollection[i] = i;

        assertDoesNotThrow(() ->  map((Integer i) -> i + 1, list(bigCollection)));
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

    @Test
    @DisplayName("count returns 0 for an empty seq")
    void testCountEmpty() {
        assertEquals(0, count(list()));
    }

    // @Test
    // @DisplayName("vector with no arguments returns an empty vector")
    // void testEmptyVec() {
    //     assertEquals(0, count(vector()));
    // }

    @Test
    @DisplayName("You can return elements from the vector")
    void testIndexable() {
        //        assertEquals(0, get(vector(0, 1, 2), 0));
    }
}
