package queue;

import list.XList;

import java.util.Iterator;

public class XListQueue<T> implements XQueue<T> {

    XList<T> data;

    public XListQueue(XList<T> data) {
        this.data = data;
    }

    @Override
    public boolean enqueue(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
        data.add(element);
        return true;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return data.remove(0);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return data.get(0);
    }
    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public XQueue<T> copy() {
        return new XListQueue<>(data.copy());
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < data.size();
            }

            @Override
            public T next() {
                return data.get(index++);
            }
        };
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
