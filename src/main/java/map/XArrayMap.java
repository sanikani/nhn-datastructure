package map;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class XArrayMap<K, V> implements XMap<K, V> {

    Entry<K,V>[] table;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAPACITY_MULTIPLE = 2;
    //새로운 요소가 추가될 인덱스
    private int nowIndex;

    private void expandsCapacity() {
        table = Arrays.copyOf(table, table.length * CAPACITY_MULTIPLE);
    }

    private boolean isFull() {
        return nowIndex >= table.length;
    }

    static class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public XArrayMap() {
        table = new Entry[DEFAULT_CAPACITY];
        nowIndex = 0;
    }

    @Override
    public V put(K key, V value) {
        if (!isValidKeyAndValue(key, value)) {
            throw new NullPointerException();
        }
        if (isFull()) {
            expandsCapacity();
        }
        if (containsKey(key) && !isEmpty()) {
            int index = getIndexByKey(key);
            table[index].setValue(value);
            return value;
        }
        Entry<K, V> entry = new Entry<>(key, value);
        table[nowIndex++] = entry;
        return value;
    }

    private boolean isValidKeyAndValue(K key, V value) {
        return key != null && value != null;
    }

    private int getIndexByKey(K key) {
        if (!isValidKey(key)){
            throw new NullPointerException();
        }
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < nowIndex; i++) {
            if (table[i].getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isValidKey(K key) {
        return !(key == null);
    }

    @Override
    public V get(K key) {
        for (int i = 0; i < nowIndex; i++) {
            if (table[i].getKey().equals(key)) {
                return table[i].getValue();
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (!isValidKey(key)) {
            throw new NullPointerException();
        }
        for (int i = 0; i < nowIndex; i++) {
            if (table[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        if (!isValidValue(value)) {
            throw new NullPointerException();
        }
        for (int i = 0; i < nowIndex; i++) {
            if (table[i].getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidValue(V value) {
        return !(value == null);
    }

    @Override
    public V remove(K key) {
        int indexByKey = getIndexByKey(key);

        V value = null;
        if (indexByKey == -1) {
            return value;
        }

        value = table[indexByKey].getValue();
        table[indexByKey] = null;

        //뒤의 값 모두 한칸씩 당기기
        for (int i = indexByKey; i < nowIndex; i++) {
            table[i] = table[i + 1];
        }

        nowIndex--;
        return value;
    }

    @Override
    public void clear() {
        table = new Entry[]{};
        nowIndex = 0;
    }

    @Override
    public int size() {
        return nowIndex;
    }

    @Override
    public boolean isEmpty() {
        return nowIndex == 0;
    }

    @Override
    public Set<K> keySet() {
        K[] keys = (K[]) new Object[nowIndex];
        for (int i = 0; i < nowIndex; i++) {
            keys[i] = table[i].getKey();
        }
        return Set.of(keys);
    }

    @Override
    public Set<V> values() {
        V[] values = (V[]) new Object[nowIndex];
        for (int i = 0; i < nowIndex; i++) {
            values[i] = table[i].getValue();
        }
        return Set.of(values);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < nowIndex; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(table[i].getKey()).append("=").append(table[i].getValue());
        }
        sb.append("}");
        return sb.toString();
    }
}
