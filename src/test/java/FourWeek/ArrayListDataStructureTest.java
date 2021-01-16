package FourWeek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListDataStructureTest {

    ArrayListDataStructure<Integer> arrayList;

    @BeforeEach
    void setUp() {
        arrayList = new ArrayListDataStructure<>();

    }

    @Test
    public void addTest() {
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);
        arrayList.add(50);
        arrayList.add(60);
        arrayList.add(4,45);
        System.out.println(arrayList);
    }

    @Test
    public void addFirstTest() {
        arrayList.addFirst(10);
        assertEquals(10, arrayList.get(0));

        arrayList.addFirst(20);
        assertEquals(20, arrayList.get(0));

        arrayList.addFirst(30);
        assertEquals(30, arrayList.get(0));
    }

    @Test
    public void removeTest() {
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);

        assertEquals(30, arrayList.remove(2));
        assertEquals(10, arrayList.remove(0));

        System.out.println(arrayList);
    }

    @Test
    public void sizeTest() {
        assertEquals(0, arrayList.size());

        arrayList.add(10);
        assertEquals(1, arrayList.size());
    }

    @Test
    public void indexOfTest() {

        arrayList.add(30);
        arrayList.add(20);
        arrayList.add(10);
        assertEquals(2, arrayList.indexOf(10));
        assertEquals(-1, arrayList.indexOf(40));
    }

    @Test
    public void iteratorTest() {
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);

        ArrayListDataStructure.IteratorDataStructure iterator = arrayList.listIterator();
        assertEquals(10, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(40, iterator.next());
        assertNull(iterator.next());

    }

    @Test
    public void previousTest() {
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(40);

        ArrayListDataStructure<Integer>.IteratorDataStructure iterator = arrayList.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
        }

        assertEquals(40,iterator.previous());
        assertEquals(30,iterator.previous());
        assertEquals(20,iterator.previous());
        assertEquals(10,iterator.previous());
        assertThrows(ArrayIndexOutOfBoundsException.class, iterator::previous);
    }
}