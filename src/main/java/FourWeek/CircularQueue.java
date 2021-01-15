package FourWeek;

public class CircularQueue {

    private int[] queueArray;
    private int front;
    private int rear;

    public CircularQueue() {
        this(10);
    }

    public CircularQueue(int size){
        queueArray = new int[size + 1];
        front = 0;
        rear = 0;
    }

    private int nextIndex(int pos){
        if (pos == queueArray.length -1){
            return 0;
        }else{
            return pos + 1;
        }
    }

    public boolean empty(){
       return front == rear;
    }

    public boolean isFull(){
        return ((rear + 1) % queueArray.length) == front;
    }

    public void enqueue(int data){

        if (nextIndex(rear) == front){
            throw new ArrayIndexOutOfBoundsException("큐 꽉참");
        }
        rear = nextIndex(rear);
        queueArray[rear] = data;
    }

    public int dequeue(){

        if (empty()){
            throw new ArrayIndexOutOfBoundsException("큐 배열 오류");
        }

        front = nextIndex(front);
        return queueArray[front];
    }

    public int peek() {
        if (empty()){
            throw new ArrayIndexOutOfBoundsException("큐 배열이 비어있습니다.");
        }
        return queueArray[nextIndex(front)];

    }
}
