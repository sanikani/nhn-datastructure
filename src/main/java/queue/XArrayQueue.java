package queue;

import java.util.Iterator;

public class XArrayQueue<T> implements XQueue<T>{

    private static final int INITIAL_CAPACITY = 10;
    private static final int CAPACITY_MULTIPLE = 2;
    private Object[] data;

    //마지막 요소가 저장된 위치
    private int rear;
    private int front;

    public XArrayQueue() {
        this.data = new Object[INITIAL_CAPACITY];
        rear = -1;
        front = 0;
    }

    public XArrayQueue(int capacity) {
        this.data = new Object[capacity];
        rear = -1;
        front = 0;
    }

    private void expandsCapacity() {
        Object[] newData = new Object[data.length * CAPACITY_MULTIPLE];
        int size = size();
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        rear = size - 1;
    }

    private boolean isFull() {
        return front == (rear + 1) % data.length;
    }

    @Override
    public boolean enqueue(T element) {
        if (isInvalidElement(element)) {
            throw new NullPointerException();
        }
        if (isFull()) {
            expandsCapacity();
        }
        data[(rear + 1) % data.length] = element;
        rear = (rear + 1) % data.length;
        return true;
    }

    private boolean isInvalidElement(T element) {
        return element == null;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        T result = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        return result;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return (T) data[front];
    }

    @Override
    public int size() {
        return rear - front + 1;
    }

    @Override
    public boolean isEmpty() {
        return front == rear + 1;
    }

    @Override
    public void clear() {
        data = new Object[INITIAL_CAPACITY];
        rear = -1;
        front = 0;
    }

    @Override
    public XQueue<T> copy() {
        XQueue<T> xQueue = new XArrayQueue<>(data.length);
        for (int i = 0; i < size(); i++) {
            xQueue.enqueue((T) data[(front + i) % data.length]);
        }
        return xQueue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = front;

            @Override
            public boolean hasNext() {
                return index <= rear;
            }

            @Override
            public T next() {
                return (T) data[index++];
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(data[(front + i) % data.length]);
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
