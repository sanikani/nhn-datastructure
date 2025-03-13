package tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class XBinarySearchTree<T extends Comparable<T>> implements XBinaryTree<T> {

    private static class Node<T>{
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value){
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node<T> root;
    private int size;

    public XBinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        } else {
            if (value.compareTo(node.value) < 0) {
                node.left = insert(node.left, value);
            } else if (value.compareTo(node.value) > 0) {
                node.right = insert(node.right, value);
            }
        }

        return node;
    }

    @Override
    public boolean search(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        return search(root, value);
    }

    private boolean search(Node<T> node, T value) {
        if (node == null) {
            return false;
        } else if (node.value.equals(value)) {
            return true;
        } else if (value.compareTo(node.value) < 0) {
            return search(node.left, value);
        } else {
            return search(node.right, value);
        }
    }

    @Override
    public void delete(T value) {
        Objects.requireNonNull(value, "Value cannot be null");
        root = delete(root, value);
    }

    private Node<T> delete(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        if (value.compareTo(node.value) < 0) {
            node.left = delete(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node<T> minNode = findMin(node.right);
            node.value = minNode.value;
            node.right = delete(node.right, node.value);
            size--;
        }

        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node<T> root) {
        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(height(root.left), height(root.right));
        }
    }

    @Override
    public List<T> inorderTraversal() {
        List<T> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(Node<T> node, List<T> list) {
        if (node != null) {
            inorderTraversal(node.left, list);
            list.add(node.value);
            inorderTraversal(node.right, list);
        }
    }

    public static void main(String[] args) {

    }
}
