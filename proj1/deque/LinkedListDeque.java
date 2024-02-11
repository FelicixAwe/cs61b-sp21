package deque;

import java.util.Iterator;

public class LinkedListDeque < T > implements Deque < T >, Iterable< T > {
    private Node < T > sentinel;
    private int size = 0;

    public LinkedListDeque() {
        sentinel = new Node < > (null, null, null);
        sentinel.next = sentinel.prev = sentinel;
    }
    public void addFirst(T item) {
        Node < T > newNode = new Node < > (item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node < T > newNode = new Node < > (item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node < T > cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node < T > prevFirst = sentinel.next;
        prevFirst.next.prev = sentinel;
        sentinel.next = prevFirst.next;
        size--;
        return prevFirst.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node < T > prevLast = sentinel.prev;
        prevLast.prev.next = sentinel;
        sentinel.prev = prevLast.prev;
        size--;
        return prevLast.item;

    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        Iterator < T > iterator = iterator();
        return getRecursiveHelper(iterator, index);
    }

    private T getRecursiveHelper(Iterator < T > ite, int index) {
        T item = ite.next();
        if (index == 0) {
            return item;
        }
        return getRecursiveHelper(ite, --index);
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        T item = null;
        Iterator < T > iterator = iterator();
        for (int i = 0; i <= index; i++) {
            if (iterator.hasNext()) {
                item = iterator.next();
            }
        }
        return item;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque < T > other = (Deque < T > ) o;
        if (other.size() != size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!(other.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator < T > iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator < T > {
        private Node < T > current;

        public LinkedListDequeIterator() {
            this.current = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T returnItem = current.item;
            current = current.next;
            return returnItem;
        }
    }


    private static class Node < T > {
        public T item;
        public Node < T > next;
        public Node < T > prev;

        public Node(T item, Node < T > prev, Node < T > next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Node(T item) {
            this.item = item;
            this.next = this.prev = null;
        }
    }
}
