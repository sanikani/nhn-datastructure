package list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

public class XLinkedList<T> implements XList<T>{

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.data = element;
            this.next = next;
            this.prev = prev;
        }

        public void setData(T element){
            data = element;
        }

        public Node<T> getNode() {
            return this;
        }
    }

    public XLinkedList() {
        size = 0;
        first = null;
        last = null;
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    @Override
    public void add(T element) {
        isValidElement(element);
        Node<T> node = new Node<>(null, element, null);
        if (isEmpty()) {
            this.first = node;
            this.last = node;
        }else if(first==last){
            first.next = node;
            last = node;
            last.prev = first;
        }else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
    }

    @Override
    public void add(int index, T element) {
        isValidIndex(index);
        if (index == 0) {
            addFirst(element);
            return;
        }
        if (index == size) {
            add(element);
            return;
        }
        int count = 0;
        Node<T> currentNode = first;
        Node<T> addNode = new Node<>(null, element, null);
        while (!(currentNode == null)) {
            if (count == index) {
                addNode.next = currentNode;
                addNode.prev = currentNode.prev;
                currentNode.prev.next = addNode;
                currentNode.prev = addNode;
            }
            currentNode = currentNode.next;
            count++;
        }
        size++;
    }

    public void addFirst(T element) {
        Node<T> addNode = new Node<>(null, element, null);
        addNode.next = first;
        first.prev = addNode;
        first = addNode;
        size++;
    }

    @Override
    public T remove(int index) {
        isValidIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<T> currentNode = first;
        int count = 0;
        while (currentNode != null) {
            if (count == index) {
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                size--;
                return currentNode.data;
            }
            currentNode = currentNode.next;
            count++;
        }
        return null;
    }

    private T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        T data = first.data;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return data;
    }

    private T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        T data = last.data;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return data;
    }

    @Override
    public boolean remove(T element) {
        isValidElement(element);
        if (isEmpty()) {
            return false;
        }

        if (first.data.equals(element)) {
            removeFirst();
            return true;
        }
        Node<T> currentNode = first.next;
        while (currentNode != null && currentNode != last) {
            if (currentNode.data.equals(element)) {
                currentNode.prev.next = currentNode.next;
                currentNode.next.prev = currentNode.prev;
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }

        if (last.data.equals(element)) {
            removeLast();
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        Node<T> currentNode = first;
        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        Node<T> currentNode = first;
        int index = 0;
        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return -1;
    }

    @Override
    public T get(int index) {
        isValidIndex(index);
        Node<T> currentNode = first;
        int count = 0;

        while (currentNode != null) {
            if (count == index) {
                return currentNode.data;
            }
            currentNode = currentNode.next;
            count++;
        }

        return null;
    }

    @Override
    public void set(int index, T element) {
        isValidElement(element);
        isValidIndex(index);

        Node<T> currentNode = first;
        int count = 0;

        while (currentNode != null) {
            if (count == index) {
                currentNode.setData(element);
            }
            currentNode = currentNode.next;
            count++;
        }
    }

    @Override
    public void sort(Comparator<T> comparator) {
        Object[] nodeData = toArray();
        Arrays.sort((T[])nodeData,comparator);
        int index=0;
        for (Node<T> x = first; x != null; x = x.next){
            x.data = (T) nodeData[index++];
        }
    }

    @Override
    public XList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        XLinkedList<T> subList = new XLinkedList<>();
        Node<T> toNode = getNode(toIndex);
        for (Node<T> x = getNode(fromIndex); x != toNode; x = x.next) {
            subList.add(x.data);
        }
        return subList;
    }

    @Override
    public void addAll(XList<T> otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            this.add(otherList.get(i));
        }
    }

    @Override
    public void forEach(Consumer<T> action) {
        for (Node<T> x = first; x != null; x = x.next) {
            action.accept(x.data);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public XList<T> copy() {
        XLinkedList<T> xList = new XLinkedList<>();
        if (!isEmpty()) {
            for (Node<T> x = first; x != null; x = x.next) {
                xList.add(x.data);
            }
        }
        return xList;
    }

    private static <T> void isValidElement(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
    }

    private void isValidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<T> x = first; x != null; x = x.next){
            result[i++] = x.data;
        }
        return result;
    }

    private Node<T> getNode(int index) {
        int i=0;
        for (Node<T> x = first; x != null; x = x.next) {
            if (i == index) {
                return x;
            }
            i++;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> currentNode = first;
        while (currentNode != null) {
            sb.append(currentNode.data);
            if (currentNode.next != null) {
                sb.append(", ");
            }
            currentNode = currentNode.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
