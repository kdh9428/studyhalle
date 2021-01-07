package FourWeek;

import java.util.NoSuchElementException;

public class LinkedListDataStructure {

    private Node head;
    private Node tail;
    private int size = 0;

    public class Node {
        private Object data;
        private Node next;

        public Node(Object element) {
            this.data = element;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public void addFirst(Object element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }
    }

    public void addLast(Object element) {
        Node newNode = new Node(element);

        if (size == 0) {
            addFirst(element);
        } else {
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    private Node node(int index) {
        Node x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x;
    }

    public void add(int index, Object element) {
        checkPositionIndex(index);
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node newNode = new Node(element);
            Node beforeNode = node(index - 1);
            newNode.next = beforeNode.next;
            beforeNode.next = newNode;
            size++;
        }
    }

    public void add(Object element) {
        Node newNode = new Node(element);

        if (size == 0) {
            addFirst(element);
        } else {
            addLast(element);
        }
    }


    public Object removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("저장된 데이터가 없습니다.");
        }
        final Node next = head.next;
        final Object data = head.data;

        head = next;
        if (next == null)
            tail = null;
        size--;
        return data;
    }

    public Object remove() {
        return removeFirst();
    }

    public Object removeLast() {
        Node previousNode = node(size - 2);
        Object data = tail.data;
        tail.data = null;
        tail.next = null;
        previousNode.next = null;

        tail = previousNode;

        size--;
        return data;
    }

    public Object remove(int index) {

        checkPositionIndex(index);

        if (index == size - 1) {
            return removeLast();
        } else if (index == 0) {
            return removeFirst();

        } else {
            Node previousNode = node(index - 1);
            Node deleteNode = previousNode.next;
            previousNode.next = deleteNode.next;
            deleteNode.next = null;
            deleteNode.data = null;
            size--;
            return deleteNode;
        }
    }

    public int size() {
        return size;
    }

    public Object get() {
        return tail.data;
    }

    public Object get(int index) {
        checkPositionIndex(index);
        return node(index).data;
    }

    public int indexOf(Object element) {
        int index = 0;

        if (element == null) {
            for (Node i = head; i != null; i = i.next) {
                if (i.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node i = head; i != null; i = i.next) {
                if (element.equals(i.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkPositionIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "범위를 벗어났습니다. Index: " + index + ", Size:" + size;
    }

    public boolean contains(Object element){
        return indexOf(element) >= 0;
    }

    @Override
    public String toString() {

        StringBuffer builder = new StringBuffer();

        if (head == null)
            return "[]";

        Node current = this.head;
        builder.append("[");
        while (current.next != null) {
            builder.append(current.data + ", ");
            current = current.next;
        }
        builder.append(current.data);
        return builder.append("]").toString();
    }

    public ListIterator listIterator() {
        return new ListIterator();
    }

    public class ListIterator {

        private Node next;
        private Node lastReturned;
        private int nextIndex;


        public ListIterator() {
            this.next = head;
            nextIndex = 0;
        }

        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;

            return lastReturned.data;
        }

        public boolean hasNext() {
            return nextIndex < size();
        }

        public void add(Object element) {
            Node newNode = new Node(element);

            if (lastReturned == null) {
                head = newNode;
                newNode.next = next;
            } else {
                lastReturned.next = newNode;
                newNode.next = next;
            }
            lastReturned = newNode;
            nextIndex++;
            size++;
        }
    }
}
