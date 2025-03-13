package set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

public class XHashSet<T> implements XSet<T> {

    private HashMap<T, Object> map;
    private static final Object DUMMY_OBJECT = new Object();

    public XHashSet() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean add(T element) {
        Objects.requireNonNull(element, "Value cannot be null");
        return map.put(element, DUMMY_OBJECT) == null;
    }

    @Override
    public boolean remove(T element) {
        Objects.requireNonNull(element, "Value cannot be null");
        return !(map.remove(element) == null);
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element, "Value cannot be null");
        return map.containsKey(element);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public XSet<T> union(XSet<T> other) {
        XSet<T> unionSet = new XHashSet<>();
        for (T t : other) {
            unionSet.add(t);
        }
        for (T t : this) {
            unionSet.add(t);
        }
        return unionSet;
    }

    @Override
    public XSet<T> intersection(XSet<T> other) {
        XSet<T> intersectionSet = new XHashSet<>();
        for (T t : other) {
            if (this.contains(t)) {
                intersectionSet.add(t);
            }
        }
        return intersectionSet;

    }

    @Override
    public XSet<T> difference(XSet<T> other) {
        XSet<T> differenceSet = this.copy();
        for (T t : other) {
            if (contains(t)) {
                differenceSet.remove(t);
            }
        }
        return differenceSet;
    }

    @Override
    public XSet<T> symmetricDifference(XSet<T> other) {
        XSet<T> symmetricDifferenceSet = new XHashSet<>();
        for (T t : other) {
            if (!contains(t)) {
                symmetricDifferenceSet.add(t);
            }
        }
        for (T t : this) {
            if (!other.contains(t)) {
                symmetricDifferenceSet.add(t);
            }
        }
        return symmetricDifferenceSet;
    }

    @Override
    public boolean isSubsetOf(XSet<T> other) {
        for (T t : this) {
            if (!other.contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSupersetOf(XSet<T> other) {
        for (T t : other) {
            if (!this.contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public XSet<T> copy() {
        XSet<T> copiedSet = new XHashSet<>();
        for (T t : this) {
            copiedSet.add(t);
        }

        return copiedSet;
    }

    @Override
    public String toString() {
        return map.toString();
    }

//    public static void main(String[] args) {
//        XSet<Integer> set = new XHashSet<>();
//
//        set.add(10);
//        set.add(20);
//        set.add(10); // 중복 허용 안 됨
//
//        System.out.println(set.contains(10)); // 출력: true
//        System.out.println(set.contains(30)); // 출력: false
//        System.out.println(set.size()); // 출력: 2
//
//        set.remove(10);
//        System.out.println(set.contains(10)); // 출력: false
//        XSet<Integer> setA = new XHashSet<>();
//        setA.add(1);
//        setA.add(2);
//
//        XSet<Integer> setB = new XHashSet<>();
//        setB.add(2);
//        setB.add(3);
//
//        XSet<Integer> unionSet = setA.union(setB);
//        System.out.println(unionSet.size()); // 출력: 3 (1,2,3)
//
//        XSet<Integer> intersectionSet = setA.intersection(setB);
//        System.out.println(intersectionSet.size()); // 출력: 1 (2)
//
//        XSet<Integer> differenceSet = setA.difference(setB);
//        System.out.println(differenceSet.size()); // 출력: 1 (1)
//
//    }
}
