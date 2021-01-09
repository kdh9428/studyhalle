package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackDataStructureTest {

    StackDataStructure stack;

    @BeforeEach
    void setUp() {
        stack = new StackDataStructure();
    }

    @Test
    public void push(){
        stack.push(10);
        stack.push(20);
    }

    @Test
    public void pop(){
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.push(70);
        assertEquals(70, stack.pop());
        assertEquals(60, stack.pop());
        assertEquals(50, stack.pop());
        assertEquals(40, stack.pop());
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void search(){
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        assertEquals(1, stack.search(40));
        assertEquals(4, stack.search(10));
    }

    @Test
    public void peek(){
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        assertEquals(40, stack.peek());

    }

    @Test
    public void empty(){
        assertTrue(stack.empty());
        stack.push(10);
        assertFalse(stack.empty());
    }
}