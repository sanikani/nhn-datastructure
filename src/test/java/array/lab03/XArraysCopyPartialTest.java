package array.lab03;

import array.arraycopy.XArrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XArraysCopyPartialTest {
    // copy(T[] source, int srcIndex, T[] destination, int destIndex, int length)
    // 테스트
    @Test
    void testCopyGenericArrayToAnotherArray() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = new String[5];
        XArrays.copy(source, 1, destination, 2, 3);
        assertArrayEquals(new String[] { null, null, "b", "c", "d" }, destination);
    }

    @Test
    void testCopyGenericArrayToSelf() {
        String[] source = { "a", "b", "c", "d", "e" };
        XArrays.copy(source, 1, source, 2, 3);
        assertArrayEquals(new String[] { "a", "b", "b", "c", "d" }, source);

        XArrays.copy(source, 2, source, 0, 3);
        assertArrayEquals(new String[] { "b", "c", "d", "c", "d" }, source);
    }

    @Test
    void testCopyGenericArrayToAnotherArray_NullSourceOrDestination() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = new String[5];
        assertThrows(NullPointerException.class, () -> XArrays.copy((String[]) null, 0, destination, 0, 0));
        assertThrows(NullPointerException.class, () -> XArrays.copy(source, 0, (String[]) null, 0, 0));
    }

    @Test
    void testCopyGenericArrayToAnotherArray_InvalidParams() {
        String[] source = { "a", "b", "c", "d", "e" };
        String[] destination = new String[5];
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, -1, destination, 0, 3));
        assertThrows(IndexOutOfBoundsException.class, () -> XArrays.copy(source, 0, destination, -1, 3));
        assertThrows(IllegalArgumentException.class, () -> XArrays.copy(source, 2, destination, 1, 10));
    }
}