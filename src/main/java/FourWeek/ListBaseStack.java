package FourWeek;

public class ListBaseStack {

    private Node head;
    private int size;

    class Node {
        Object data;
        Node next;
    }

    public ListBaseStack() {
        head = null;
    }

    public boolean empty() {
        return head == null;
    }

    public Object push(Object data) {
        Node newNode = new Node();

        newNode.data = data;
        newNode.next = head;

        head = newNode;
        size++;
        return data;
    }

    public Object pop() {

        if (empty())
            throw new ArrayIndexOutOfBoundsException("저장된 데이터가 없습니다.");
        Object data = head.data;
        head = head.next;
        return data;
    }

    public Object peek() {
        if (empty())
            throw new ArrayIndexOutOfBoundsException("저장된 데이터가 없습니다.");
        return head.data;
    }

    public int search(Object element) {
        int index = 1;
        Node headNext = head;
        for (int i = 0; i < size; i++) {
            if (headNext.data.equals(element)) {
                return index;
            }
            headNext = headNext.next;
            index++;
        }
        return -1;
    }

    public int size() {
        return size;
    }
}
