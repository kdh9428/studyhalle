package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircularQueueTest {
    CircularQueue queue;

    @BeforeEach
    void setUp() {

        queue = new CircularQueue(5);
    }

    @Test
    public void enqueueTest(){

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);

    }

    @Test
    public void dequeueTest(){

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        queue.enqueue(40);
        queue.enqueue(50);
        queue.enqueue(60);

        assertEquals(40, queue.dequeue());
        assertEquals(50, queue.dequeue());
        assertEquals(60, queue.dequeue());
        queue.enqueue(70);
        queue.enqueue(80);
        assertEquals(70, queue.dequeue());
        assertEquals(80, queue.dequeue());


    }

    @Test
    public void peekTest(){
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(10,queue.peek());
        assertEquals(10,queue.peek());
    }

    @Test
    public void pullTest(){
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        assertTrue(queue.isFull());
    }

    @Test
    public void dataExtractionTest(){

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        while(!queue.empty()){
            System.out.println(queue.dequeue());
        }
    }
}