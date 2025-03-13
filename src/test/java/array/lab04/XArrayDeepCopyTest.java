package array.lab04;

import array.arraycopy.XArrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XArrayDeepCopyTest {
    @Test
    @DisplayName("copy(Integer[]) - null 입력 시 NullPointerException 발생")
    void copyIntegerArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.copy((Integer[]) null));
    }

    @Test
    @DisplayName("copy(Integer[]) - 배열 복사 성공")
    void copyIntegerArray_Success() {
        Integer[] source = { 1, 2, 3, 4, 5 };
        Integer[] destination = XArrays.copy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");
    }

    @Test
    @DisplayName("deepCopy(int[][]) - null 입력 시 NullPointerException 발생")
    void deepCopyInt2DArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((int[][]) null));
    }


    @Test
    @DisplayName("deepCopy(Integer[][]) - null 입력 시 NullPointerException 발생")
    void deepCopyInteger2DArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((Integer[][]) null));
    }

    @Test
    @DisplayName("deepCopy(Integer[][]) - 2차원 배열 깊은 복사 성공")
    void deepCopyInteger2DArray_Success() {
        Integer[][] source = { { 1, 2 }, { 3, 4, 5 }, { 6 } };
        Integer[][] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");
        for (int i = 0; i < source.length; i++) {
            assertNotSame(source[i], destination[i], "내부 배열 깊은 복사 여부 확인");
            assertArrayEquals(source[i], destination[i]);
        }
    }

    @Test
    @DisplayName("deepCopy(Object[]) - null 입력 시 NullPointerException 발생")
    void deepCopyObjectArray_NullInput() {
        assertThrows(NullPointerException.class, () -> XArrays.deepCopy((Object[]) null));
    }

    @Test
    @DisplayName("deepCopy(Object[]) - Object 배열 깊은 복사 성공 (기본 타입 배열 포함)")
    void deepCopyObjectArray_Success_WithPrimitiveArray() {
        Object[] source = { 1, "hello", new int[] { 7, 8, 9 }, new String[] { "a", "b" } };
        Object[] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");

        // 내부 배열 깊은 복사 확인 (int[])
        assertArrayEquals((int[]) source[2], (int[]) destination[2]);
        assertNotSame(source[2], destination[2], "내부 int[] 배열 깊은 복사 여부 확인");

        // 내부 배열 깊은 복사 확인 (String[])
        assertArrayEquals((String[]) source[3], (String[]) destination[3]);
        assertNotSame(source[3], destination[3], "내부 String[] 배열 깊은 복사 여부 확인");
    }

    @Test
    @DisplayName("deepCopy(Object[]) - Object 배열 깊은 복사 성공 (Object 배열 포함)")
    void deepCopyObjectArray_Success_WithObjectArray() {
        Object[] innerArray = { "c", "d" };
        Object[] source = { 1, "hello", innerArray };
        Object[] destination = XArrays.deepCopy(source);

        assertArrayEquals(source, destination);
        assertNotSame(source, destination, "깊은 복사 여부 확인");

        // 내부 배열 깊은 복사 확인 (Object[])
        assertArrayEquals((Object[]) source[2], (Object[]) destination[2]);
        assertNotSame(source[2], destination[2], "내부 Object[] 배열 깊은 복사 여부 확인");
        if (destination[2] instanceof Object[]) {
            assertNotSame(innerArray, (Object[]) destination[2], "내부 Object 배열 깊은 복사 확인");
        }
    }
}
