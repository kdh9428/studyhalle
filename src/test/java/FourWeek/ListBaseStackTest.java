package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListBaseStackTest {

    ListBaseStack stack;

    @BeforeEach
    void setUp() {
        stack = new ListBaseStack();
    }

    @Test
    public void newNodeTest(){
        ListBaseStack stack1 = new ListBaseStack();

    }

    @Test
    public void pushTest(){

        assertEquals(10,stack.push(10));
        assertEquals(20,stack.push(20));
        assertEquals(30,stack.push(30));
        assertEquals(40,stack.push(40));

    }

    @Test
    public void popTest(){
        stack.push(10);
        stack.push(20);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    @Test
    public void peekTest(){
        stack.push(20);
        stack.push(10);

        assertEquals(10,stack.peek());
    }

    @Test
    public void searchTest(){
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        assertEquals(4, stack.search(10));
        assertEquals(3, stack.search(20));
        assertEquals(2, stack.search(30));
        assertEquals(1, stack.search(40));
    }

    @Test
    public void emptyTest(){
        assertTrue(stack.empty());
    }
}