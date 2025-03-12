package stack;

import list.XLinkedList;
import list.XList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class XLinkedStack<T> implements XStack<T> {

    private XList<T> data;
    private int top;

    public XLinkedStack() {
        data = new XLinkedList<>();
        top = -1;
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element, "Value cannot be null");
        data.add(element);
        top++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T remove = data.remove(top);
        top--;
        return remove;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return data.get(top);
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
        data.clear();
        top = -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {;
            private int index = top;
            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public T next() {
                return data.get(index--);
            }
        };
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
