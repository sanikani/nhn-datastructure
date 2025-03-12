package list;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class XArrayList<T> implements XList<T>{

    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAPACITY_MULTIPLE = 2;
    //새로운 요소가 추가될 인덱스
    private int nowIndex;
    Object[] data;

    public XArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        nowIndex = 0;
    }

    public XArrayList(int capacity) {
        data = new Object[capacity];
        nowIndex = 0;
    }

    private void expandsCapacity() {
        data = Arrays.copyOf(data, data.length * CAPACITY_MULTIPLE);
    }

    private boolean isFull() {
        return nowIndex >= data.length;
    }

    @Override
    public void add(T element) {
        isValidElement(element);
        if (isFull()) {
            expandsCapacity();
        } else {
            data[nowIndex++] = element;
        }
    }

    @Override
    public void add(int index, T element) {
        isValidIndex(index);
        if (isFull()) {
            expandsCapacity();
        } else {
            nowIndex++;
            Object tmp = data[index];
            data[index] = element;
            for (int i = index+1; i < nowIndex; i++) {
                data[i] = tmp;
                tmp = data[i+1];
            }
        }
    }

    @Override
    public T remove(int index) {
        isValidIndex(index);
        T tmp = (T) data[index];
        data[index] = null;
        for (int i = index; i < nowIndex; i++) {
            data[i] = data[i + 1];
        }
        nowIndex--;
        return tmp;
    }

    @Override
    public boolean remove(T element) {
        isValidElement(element);
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < nowIndex-1; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < nowIndex; i++) {
            if (element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        isValidIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(int index, T element) {
        if (index > 0 || index >= nowIndex) {
            throw new IllegalArgumentException();
        }
        data[index] = element;
    }

    @Override
    public void sort(Comparator<T> comparator) {
        Arrays.sort((T[]) data, 0, nowIndex, comparator);
    }

    @Override
    public XList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= nowIndex || toIndex < 0 || toIndex >= nowIndex || fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        XList<T> subList = new XArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add((T)data[i]);
        }
        return subList;
    }

    @Override
    public void addAll(XList<T> otherList) {
        //넣다가 최대 크기에 도달하면 확장
        while (!otherList.isEmpty()) {
            if (isFull()) {
                expandsCapacity();
            } else {
                data[nowIndex++] = otherList.remove(0);
            }
        }
    }

    @Override
    public void forEach(Consumer<T> action) {

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
    public void clear() {
        data = new Object[DEFAULT_CAPACITY];
        nowIndex = 0;
    }

    @Override
    public XList<T> copy() {
        //배열 복사 후 새로운 XList 생성
        //생성자에 자료구조를 파라미터로 받게? 아니면 하나씩 넣게?
        XArrayList<T> copiedList = new XArrayList<>();
        copiedList.data = copyArray(this.data);
        copiedList.nowIndex = this.size();
        return copiedList;
    }

    public <V> V[] copyArray(V[] source){
        V[] target = (V[]) Array.newInstance(source.getClass().getComponentType(), source.length);
        System.arraycopy(source, 0, target, 0, source.length);
        return target;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    private void isValidIndex(int index) {
        if (index < 0 || index >= nowIndex) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static <T> void isValidElement(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
    }
}
