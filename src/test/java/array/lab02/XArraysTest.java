package array.lab02;

import array.arraycopy.XArrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XArraysTest {
    @Test
    void testCopyGenericArrayWithStartAndLength() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = XArrays.copy(source, 1, 3);
        assertArrayEquals(new String[] { "b", "c", "d" }, destination);
    }

    @Test
    void testCopyGenericArrayWithStartAndLength_NullSource() {
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy((String[]) null, 0, 0));
    }

    @Test
    void testCopyGenericArrayWithStartAndLength_InvalidParams() {
        String[] source = { "a", "b", "c", "d", "e" };
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, -1, 3));
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy(source, 2, 10));
    }
}