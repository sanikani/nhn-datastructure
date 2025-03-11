package array.lab04;

import array.arraycopy.XArrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XArrayDeepCopyTest {
    // deepCopy(Object[] source) 테스트
    @Test
    void testDeepCopyObjectArray() {
        String[] arr1 = { "a" };
        String[] arr2 = { "c", "d" };
        Object[][] source = { arr1, arr2 };
        Object[] destination = XArrays.deepCopy(source);

        assertArrayEquals((String[]) source[0], (String[]) destination[0]);
        assertArrayEquals((String[]) source[1], (String[]) destination[1]);

        ((String[]) destination[0])[0] = "x";
        assertNotEquals(((String[]) source[0])[0], ((String[]) destination[0])[0]); // Deep copy 확인
    }

    @Test
    void testDeepCopyObjectArray_NullSource() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((Object[][]) null));
    }

    @Test
    void testDeepCopyObjectArray_NestedArray() {
        String[] arr1 = { "a", "b" };
        Object[] arr2 = { 1, 2 };
        Object[][] source = { arr1, arr2 };
        Object[] destination = XArrays.deepCopy(source);

        assertArrayEquals((String[]) source[0], (String[]) destination[0]);
        assertArrayEquals((Object[]) source[1], (Object[]) destination[1]);

        ((String[]) destination[0])[0] = "x";
        assertNotEquals(((String[]) source[0])[0], ((String[]) destination[0])[0]); // Deep copy 확인
    }
}