package FourWeek;

import java.util.Arrays;

public class ArrayListDataStructure<T> {

    private int MAX_DATA_LENGTH = 5;
    private Object[] listData;
    private int numOfData;
    private int curPosition;

    public Object[] getListData (){
        return listData;
    }
    public ArrayListDataStructure() {
        this.numOfData = 0;
        this.curPosition = -1;
        listData = new Object[MAX_DATA_LENGTH];
    }

    public boolean addFirst(T num) {
        return add(0, num);
    }

    public boolean add(T num) {
        if (numOfData >= MAX_DATA_LENGTH) {
            listData = Arrays.copyOf(listData, MAX_DATA_LENGTH * 2);
            MAX_DATA_LENGTH = listData.length;
        }
        listData[numOfData] = num;
        numOfData++;
        return true;
    }

    public boolean add(int index, T num) {

        checkIndexRange(index);


        for (int i = numOfData - 1; i >= index; i--) {
            listData[i + 1] = listData[i];
        }

        listData[index] = num;
        numOfData++;

        return true;
    }

    private void checkIndexRange(int index) {

        if (index < 0 || index > numOfData) {
            throw new IndexOutOfBoundsException("인덱스 오류! index : " + index + " 사이즈 : " + numOfData);
        }
    }

    @Override
    public String toString() {


        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numOfData; i++) {

            builder.append(listData[i]);
            if (i < numOfData - 1) {
                builder.append(", ");
            }
        }
        return "[" + builder + "]";
    }

    public T remove(int index) {

        checkIndexRange(index);
        T removed = (T) listData[index];

        for (int i = index + 1; i <= numOfData - 1; i++) {
            listData[i - 1] = listData[i];
        }

        numOfData--;
        listData[numOfData] = 0;
        return removed;
    }

    public int size() {
        return numOfData;
    }

    public T get(int index) {
        checkIndexRange(index);
        return (T) listData[index];
    }

    public int indexOf(T num) {
        for (int i = 0; i <= numOfData; i++) {
            if (num.equals(listData[i])) {
                return i;
            }
        }
        return -1;
    }

    public IteratorDataStructure listIterator(){

        return new IteratorDataStructure();
    }


    class IteratorDataStructure {

        private int nextIndex = 0;

        public T next() {
            return (T) listData[nextIndex++];
        }

        public boolean hasNext() {
            return nextIndex < size();
        }

        public T previous() {
            return (T) listData[--nextIndex];
        }

        public boolean hasPrevious(){
            return nextIndex > 0;
        }

        public T remove(){
            return (T) ArrayListDataStructure.this.remove(nextIndex);
        }

        public void add(T element) {
            ArrayListDataStructure.this.add(nextIndex++, element);
        }
    }
}
