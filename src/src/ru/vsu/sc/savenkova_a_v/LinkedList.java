package src.ru.vsu.sc.savenkova_a_v;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    public static class LinkedListException extends Exception {
        public LinkedListException(String errorReport) {
            super(errorReport);
        }
    }

    private class LinkedListNode {
        public T value;
        public LinkedListNode next;

        public LinkedListNode(T value, LinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public LinkedListNode(T value) {
            this(value, null);
        }
    }


    private LinkedListNode head = null;
    private LinkedListNode tail = null;
    private int size = 0;

    public void addFirst(T value) {
        head = new LinkedListNode(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(T value) {
        if (size == 0) {
            head = tail = new LinkedListNode(value);
        } else {
            tail.next = new LinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    public void isEmptyList() throws LinkedListException {
        if (size == 0) {
            throw new LinkedListException("List is empty");
        }
    }

    public LinkedListNode getNode(int index) {
        LinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public void removeFirst() throws LinkedListException {
        isEmptyList();
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws LinkedListException {
        isEmptyList();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    public void remove(int index) throws LinkedListException {
        isEmptyList();
        if (index < 0 || index >= size) {
            throw new LinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            LinkedListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            size--;
        }
    }

    public int size() {
        return this.size;
    }

    public T get(int index) throws LinkedListException {
        isEmptyList();
        return this.getNode(index).value;
    }

    public T getFirst() throws LinkedListException {
        isEmptyList();
        return head.value;
    }

    public T getLast() throws LinkedListException {
        isEmptyList();
        return tail.value;
    }

    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            LinkedListNode curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        }

        return new LinkedListIterator();
    }
}


