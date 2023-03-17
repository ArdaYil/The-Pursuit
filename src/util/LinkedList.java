package util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int count = 0;

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);

        count++;

        if (this.first != null) {
            node.setNext(this.first);
            this.first.setPrev(node);
        }

        this.first = node;

        if (last == null) {
            this.last = node;
        }
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value);

        count++;

        if (this.last != null) {
            this.last.setNext(node);
            node.setPrev(this.last);
        }

        this.last = node;

        if (this.first != null) {
            return;
        }

        else {
            this.first = this.last;
        }

        this.last = node;
    }

    public T deleteFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }

        count--;

        T firstValue = this.first.getValue();

        Node<T> node = this.first.getNext();

        first.setNext(null);

        if (node == null) {
            this.first = null;
            this.last = null;

            return firstValue;
        }

        node.setPrev(null);

        this.first = node;

        return firstValue;
    }

    public T deleteLast() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }

        count--;

        T lastValue = this.last.getValue();
        Node<T> prev = this.last.getPrev();

        if (prev != null) {
            prev.setNext(null);

            this.last = prev;
        }

        else {
            this.first = this.last = null;
        }

        return lastValue;
    }

    public T getFirst() {
        if (this.isEmpty()) throw new IllegalStateException();

        return this.first.getValue();
    }

    public T getLast() {
        if (this.isEmpty()) throw new IllegalStateException();

        return this.last.getValue();
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    public int indexOf(T value) {
        Node<T> currentNode = this.first;

        int index = 0;

        while (true) {
            if (currentNode == null) return -1;

            T nodeValue = currentNode.getValue();

            if (nodeValue.equals(value) || nodeValue == value) return index;

            Node<T> next = currentNode.getNext();

            currentNode = next;
            index++;
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= this.size()) throw new IllegalArgumentException();

        Node<T> currentValue = this.first;

        if (index == 0) {
            return this.deleteFirst();
        }

        if (index == this.size() - 1) {
            return this.deleteLast();
        }

        for (int i = 0; i < this.size(); i++) {
            if (i == index) {
                Node<T> prev = currentValue.getPrev();
                Node<T> next = currentValue.getNext();

                prev.setNext(next);
                next.setPrev(prev);

                return currentValue.getValue();
            }

            currentValue = currentValue.getNext();
        }

        return null;
    }

    public T[] toArray() {
        T[] array = (T[])new Object[this.count];

        Node<T> current = this.first;

        int index = 0;

        while (current != null) {
            array[index++] = current.getValue();
            current = current.getNext();
        }

        return array;
    }

    public void reverse() {
        // h -> 1 -> <- 2 -> <- 3 <- t
        Node<T> current = this.first;
        Node<T> previousNode = null;

        while (current != null) {
            Node<T> next = current.getNext();
            current.setNext(previousNode);
            current.setPrev(next);
            previousNode = current;
            current = next;
        }

        this.last = this.first;
        this.first = previousNode;
    }

    public Node<T> kthNode(int k) {
        if (k <= 0 || k > count) throw new IllegalArgumentException();

        if (k == 1) return this.last;
        if (k == this.count) return this.first;

        int distance = k - 1;

        Node<T> pointer1 = this.first;
        Node<T> pointer2 = this.first;

        for (int i = 0; i < distance; i++) pointer2 = pointer2.getNext();

        while (true) {
            if (pointer2.getNext() == null) return pointer1;

            pointer1 = pointer1.getNext();
            pointer2 = pointer2.getNext();
        }
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public int size() {
        return this.count;
    }

    @Override
    public String toString() {
        String output = "Linked List: [ ";

        Node<T> currentNode = this.first;

        while (true) {
            if (currentNode == null) break;

            output += currentNode.getValue() + ", ";

            if (currentNode.getNext() == null) break;

            currentNode = currentNode.getNext();
        }

        output += "]";
        output = output.replace(", ]", " ]");

        return output;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {
            private Node<T> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                Node<T> next = current.getNext();
                T value = current.getValue();
                current = next;

                return value;
            }
        };
    }

    public class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return this.next;
        }

        public Node<T> getPrev() {
            return this.prev;
        }

        public T getValue() {
            return this.value;
        }

        public void setNext(Node<T> node) {
            this.next = node;
        }

        public void setPrev(Node<T> node) {
            this.prev = node;
        }

        @Override
        public String toString() {
            Node<T> next = this.getNext();
            T nodeValue = (next != null) ? next.getValue() : null;

            return "Node: { value: " + this.getValue() + ", next: " + nodeValue + " }";
        }
    }
}
