package kalimantan;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


class CoreTests {

    @Test
    @DisplayName("first returns the first element in a Seq")
    void test() {
        var seq = new Cell(1);
        assertEquals(1, Core.first(seq));
    }
}
