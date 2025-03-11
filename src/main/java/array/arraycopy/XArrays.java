package array.arraycopy;

import java.lang.reflect.Array;
import java.util.Arrays;

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
        System.out.println(source.getClass());
        System.out.println(source.getClass().getComponentType());
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


}
