package array.lab06;

import array.arrayequals.XArrays;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class XArrayEqualsTwoDimensionTest {
    @Test
    void testEquals_TwoDimensionalArray_True() {
        Integer[][] array1 = { {1, 2}, {3, 4} };
        Integer[][] array2 = { {1, 2}, {3, 4} };
        assertTrue(XArrays.equals(array1, array2));
    }

    @Test
    void testEquals_TwoDimensionalArray_False() {
        Integer[][] array1 = { {1, 2}, {3, 4} };
        Integer[][] array2 = { {1, 2}, {4, 3} };
        assertFalse(XArrays.equals(array1, array2));
    }

    @Test
    void testEquals_TwoDimensionalArray_DifferentLengths() {
        Integer[][] array1 = { {1, 2}, {3, 4} };
        Integer[][] array2 = { {1, 2} };
        assertFalse(XArrays.equals(array1, array2));
    }

    @Test
    void testEquals_TwoDimensionalArray_NullArray1() {
        Integer[][] array1 = null;
        Integer[][] array2 = { {1, 2}, {3, 4} };
        assertThrows(IllegalArgumentException.class, () -> XArrays.equals(array1, array2));
    }

    @Test
    void testEquals_TwoDimensionalArray_NullArray2() {
        Integer[][] array1 = { {1, 2}, {3, 4} };
        Integer[][] array2 = null;
        assertThrows(IllegalArgumentException.class, () -> XArrays.equals(array1, array2));
    }

    @Test
    void testEquals_TwoDimensionalArray_BothNull() {
        Integer[][] array1 = null;
        Integer[][] array2 = null;
        assertThrows(IllegalArgumentException.class, () -> XArrays.equals(array1, array2));
    }
}
