package FourWeek;

import java.util.Arrays;
import java.util.EmptyStackException;

public class StackDataStructure {

    private int[] elementData;
    private int elementCount;

    public StackDataStructure() {
        this(5);
    }

    public StackDataStructure(int size) {
        elementData = new int[size];
        this.elementCount = -1;
    }

    public int push(int item) {

        int length = elementData.length;

        if (length == elementCount + 1) {
            elementData = Arrays.copyOf(elementData, length * 2);
        }

        addElement(item);
        return item;
    }

    private void addElement(int item) {
        elementCount++;
        elementData[elementCount] = item;
    }

    public int peek() {
        return elementData[elementCount];
    }

    public int pop() {

        if (elementCount <=-1)
            throw new EmptyStackException();

        int data = peek();
        elementData[elementCount] = 0;
        elementCount--;

        return data;
    }

    public int search(int element) {
        int index = elementCount + 1;
        for (int e : elementData) {
            index--;
            if (e == element) {
                return index;
            }

        }
        return index;
    }

    public boolean empty() {
        return elementCount == -1;
    }
}
