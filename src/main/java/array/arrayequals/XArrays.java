package array.arrayequals;

import java.util.ArrayList;
import java.util.List;

public class XArrays {
    public static <T> boolean equals(T[] array1, T[] array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException();
        }
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean equals(T[][] array1, T[][] array2){
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException();
        }
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (array1[i].length != array2[i].length) {
                return false;
            }
            for (int j = 0; j < array1[i].length; j++) {
                if (array1[i][j] != array2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static boolean equals(Object array1, Object array2) {
//        Class<?> aClass = array1.getClass();
//        Class<?> bClass = array2.getClass();
//        int aDimension = 0;
//        int bDimension = 0;
//        while (aClass != null && bClass != null) {
//            aClass = aClass.getComponentType();
//            bClass = bClass.getComponentType();
//            aDimension++;
//            bDimension++;
//        }
//        if (aDimension != bDimension) {
//            return false;
//        }
//
//
//
//        return true;
//    }
//
//    public static void main(String[] args) {
//        Integer[][] array1= {
//                {1, 2, 3},
//                {1, 2, 3}
//        };
//
//        Integer[][] array2= {
//                {1, 2, 3},
//                {1, 2, 3}
//        };
//
//        equals(array1, array2);
//    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add(null);
        System.out.println(list.getFirst());
        list.add(0, "d");
        System.out.println(list);
        list.add(0, "s");
        System.out.println(list);

    }
}
