package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListQueueTest {

    ListQueue queue;

    @BeforeEach
    void setUp() {
        queue = new ListQueue();
    }

    @Test
    public void enqueueTest() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
    }

    @Test
    public void dequeueTest() {
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());

        queue.enqueue(40);
        queue.enqueue(50);
        assertEquals(40, queue.dequeue());
        assertEquals(50, queue.dequeue());

    }

    @Test
    public void empty() {
        assertTrue(queue.empty());
        queue.enqueue(10);
        assertFalse(queue.empty());
    }

    @Test
    public void peek(){
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        assertEquals("a", queue.peek());
        assertEquals("a", queue.peek());

    }

}