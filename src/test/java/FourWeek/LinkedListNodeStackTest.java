package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListNodeStackTest {

    LinkedListNodeStack stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedListNodeStack();
    }

    @Test
    public void pushTest() {

        assertEquals(10,stack.push(10));
        assertEquals(20,stack.push(20));
        assertEquals(30,stack.push(30));
        assertEquals(40,stack.push(40));

    }

    @Test
    public void popTest(){

        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }

    }

    @Test
    public void peekTest(){
        stack.push("test code");
        assertEquals("test code", stack.peek());
    }

    @Test
    public void searchTest(){
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push("check the index");

        assertEquals(4,stack.search(10));
        assertEquals(3,stack.search(20));
        assertEquals(2,stack.search(30));
        assertEquals(1,stack.search("check the index"));
        assertEquals(-1,stack.search(1));
    }

    @Test
    public void emptyTest(){

        assertTrue(stack.empty());

        stack.push(10);
        assertFalse(stack.empty());

    }
}