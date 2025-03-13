package array.arraycopy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class XArrays {
    public static int[] copy(int[] source){
        int[] target = new int[source.length];

        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
        return target;
    }
    public static <T> T[] copy(T[] source){
        T[] target = (T[]) Array.newInstance(source.getClass().getComponentType(), source.length);
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
        return target;
    }

    public static <T> T[] copy(T[] source, int startIndex, int length) {
        if (source == null || startIndex + length > source.length) {
            throw new IllegalArgumentException();
        }

        T[] target = (T[]) Array.newInstance(source.getClass().getComponentType(), length);
        for (int i = 0; i < length; i++) {
            target[i] = source[startIndex++];
        }

        return target;
    }

    public static <T> void copy(T[] source, int srcIndex, T[] destination, int destIndex, int length) {
        if (source == null || destination == null) {
            throw new NullPointerException();
        }
        if (length < 0 || srcIndex + length > source.length || destIndex + length > destination.length) {
            throw new IllegalArgumentException();
        }

        T[] copy = (T[]) Array.newInstance(source.getClass().getComponentType(), length);
        for (int i = 0; i < length; i++) {
            copy[i] = source[srcIndex++];
        }
        for (int i = 0; i <length; i++) {
            destination[destIndex++] = copy[i];
        }
    }

    public static <T> T[][] deepCopy(T[][] source) {
        T[][] target = (T[][])Array.newInstance(source.getClass().getComponentType(), source.length);
        for (int i = 0; i < source.length; i++) {
            target[i] = (T[]) Array.newInstance(source[i].getClass().getComponentType(), source[i].length);
            for (int j = 0; j < source[i].length; j++) {
                target[i][j] = source[i][j];
            }
        }
        return target;
    }

    public static Object[] deepCopy(Object[] source) {
        if (source == null) {
            throw new NullPointerException();
        }

        Object[] destination = new Object[source.length];
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null && source[i].getClass().isArray()) {
                // :rocket: 원본 배열의 타입 유지하면서 새로운 배열 생성
                destination[i] = deepCopyArray(source[i]);
            } else {
                destination[i] = source[i]; // 기본 복사
            }
        }

        return destination;
    }

    private static Object deepCopyArray(Object array) {
        int length = Array.getLength(array);

        Object copiedArray = Array.newInstance(array.getClass().getComponentType(), length);

        for (int i = 0; i < length; i++) {
            Object element = Array.get(array, i);

            if (element != null && element.getClass().isArray()) {
                // 재귀적으로 배열 복사
                Array.set(copiedArray, i, deepCopyArray(element));
            } else {
                Array.set(copiedArray, i, element);
            }
        }

        return copiedArray;
    }

    public static Object[] deepCopy2(Object[] source) {
        if (source == null) {
            throw new NullPointerException();
        }
        Object[] destination = new Object[source.length];
        while(source.length > 0) {
            destination[source.length - 1] = source[source.length - 1];
            source = Arrays.copyOf(source, source.length - 1);
        }
        return destination;
    }

}
