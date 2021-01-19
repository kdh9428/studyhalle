package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedListDataStructure 에서")
class LinkedListDataStructureTest {

    LinkedListDataStructure linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedListDataStructure();
    }

    @Nested
    @DisplayName("add 메소드로")
    class Describe {

        @Test
        @DisplayName("add() 메소드로 저장, 저장된 데이터 수로 확인한다.")
        void addTest() {
            linkedList.add(10);
            linkedList.add(20);
            linkedList.add(30);
            linkedList.add(40);
            assertEquals(4, linkedList.size());

        }

        @Test
        @DisplayName("첫 번째 인덱스에만 저장한다.")
        void addFirstTest() {

            linkedList.addFirst(10);
            linkedList.addFirst(20);
            linkedList.addFirst(30);

            assertEquals(3, linkedList.size());
            assertEquals(30, linkedList.get(0));
        }
        @Test
        @DisplayName("리스트 중간에 데이터를 저장한다.")
        void addOfIndex(){
            linkedList.add(10);
            linkedList.add(20);
            linkedList.add(30);
            linkedList.add(2,25);
            linkedList.add(3,26);

            assertEquals(5, linkedList.size());
        }

        @Test
        @DisplayName("마지막 인덱스에 저장, get메소드로 마지막 인덱스 확인한다.")
        void addLastTest() {
            linkedList.addLast(41);
            linkedList.addLast(42);

            assertEquals(42, linkedList.get());
        }
    }


    @Nested
    @DisplayName("remove 메소드로")
    class remove{

        @BeforeEach
        void setUp() {
            linkedList.add("abc");
            linkedList.add("def");
            linkedList.add("ghi");
        }

        @Test
        @DisplayName("첫 번째 인덱스에서 삭제")
        void removeFirstTest() {
            assertEquals("abc", linkedList.removeFirst());
            assertEquals("def", linkedList.removeFirst());
        }

        @Test
        @DisplayName("remove()로 삭제")
        void removeTest(){

            assertEquals("abc",linkedList.remove());
            assertEquals("def",linkedList.remove());

        }

        @Test
        @DisplayName("지정된 인덱스로 삭제한다.")
        void removeOfIndex(){
            assertEquals("def", linkedList.remove(1));
            assertEquals("abc", linkedList.remove(0));
        }

        @Test
        @DisplayName("마지막 인덱스에서 삭제")
        void removeLastTest(){
            assertEquals("ghi",linkedList.removeLast());
            assertEquals("def",linkedList.removeLast());
        }
    }

    @Nested
    @DisplayName("Iterator로")
    class Iterator{

        LinkedListDataStructure.ListIterator iterator;
        @BeforeEach
        void setUp() {
            linkedList.add(10);
            linkedList.add(20);
            linkedList.add(30);
            iterator = linkedList.listIterator();
        }

        @Test
        @DisplayName("hasNext와 next로 list를 순회한다.")
        void hasNextTest(){

            assertTrue(iterator.hasNext());
            assertEquals(10, iterator.next());
            assertEquals(20, iterator.next());
            assertEquals(30, iterator.next());
            assertThrows(NoSuchElementException.class, () -> iterator.next());
        }
    }
}