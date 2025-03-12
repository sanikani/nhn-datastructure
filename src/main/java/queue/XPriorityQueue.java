package queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class XPriorityQueue<T> implements XQueue<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAPACITY_MULTIPLE = 2;

    private Comparator<T> comparator;
    private int size;
    private T[] data;

    public XPriorityQueue(Comparator<T> comparator) {
        data = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.comparator = comparator;
        size = 0;
    }

    public XPriorityQueue() {
        data = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean enqueue(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == data.length) {
            expandCapacity();
        }
        data[size++] = element;
        int index = size - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(data[index], data[parent]) > 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
        return true;
    }

    private void swap(int index, int parent) {
        T temp = data[index];
        data[index] = data[parent];
        data[parent] = temp;
    }

    private void expandCapacity() {
        data = Arrays.copyOf(data, data.length * CAPACITY_MULTIPLE);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        T result = data[0];
        data[0] = data[--size];
        data[size] = null;

        int index = 0;
        while (true) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int min = index;
            if (left < size && comparator.compare(data[left], data[min]) < 0) {
                min = left;
            }
            if (right < size && comparator.compare(data[right], data[min]) < 0) {
                min = right;
            }
            if (min == index) {
                break;
            }
            swap(index, min);
            index = min;
        }

        return result;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return data[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        data = (T[]) new Comparable[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public XQueue<T> copy() {
        XPriorityQueue<T> copy = new XPriorityQueue<>(comparator);
        copy.data = Arrays.copyOf(data, data.length);
        copy.size = size;
        return copy;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return data[index++];
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
