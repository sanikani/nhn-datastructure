package array.lab05;

import array.arrayequals.XArrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XArraysEqualTest {
    @Test
    void testArrayEquals() {
        String[] array1 = {"a", "b", "c"};
        String[] array2 = {"a", "b", "c"};
        String[] array3 = {"a", "c", "d"};
        String[] array4 = {"a"};
        assertTrue(XArrays.equals(array1, array2));
        assertFalse(XArrays.equals(array1,array3));
        assertFalse(XArrays.equals(array1,array4));
    }

    @Test
    void testArrayEquals_DifferentType() {
        String[] array1 = {"a", "b"};
        Integer[] array2 = {1, 2};
        String[] array3 = {"1", "2"};
        assertFalse(XArrays.equals(array1, array2));
        assertFalse(XArrays.equals(array1,array3));
    }
}