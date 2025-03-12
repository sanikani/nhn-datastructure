package stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class XArrayStack<T> implements XStack<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAPACITY_MULTIPLE = 2;

    private T[] data;
    private int top;

    public XArrayStack() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    public XArrayStack(int capacity) {
        data = (T[]) new Object[capacity];
        top = -1;
    }

    private void expandCapacity() {
        data = Arrays.copyOf(data, data.length * CAPACITY_MULTIPLE);
    }

    private boolean isFull() {
        return top >= data.length - 1;
    }

    @Override
    public void push(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (isFull()) {
            expandCapacity();
        }
        data[++top] = element;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T element = data[top];
        data[top--] = null;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void clear() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = top;
            @Override
            public boolean hasNext() {
                return top >= 0;
            }

            @Override
            public T next() {
                return data[index--];
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
