package FourWeek;

public class ListQueue {

    private Node front;
    private Node rear;

    class Node {
        private Object data;
        private Node next;
    }

    public ListQueue() {
        front = null;
        rear = null;
    }

    public boolean empty() {
        return front == null;
    }

    public void enqueue(Object data) {
        Node newNode = new Node();
        newNode.next = null;
        newNode.data = data;

        if (empty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
    }

    public Object dequeue() {
        Object data;
        if (empty()) {
            throw new ArrayIndexOutOfBoundsException("큐가 비어 있습니다.");
        }
        data = front.data;
        front = front.next;
        return data;
    }

    public Object peek() {
        return front.data;
    }
}
