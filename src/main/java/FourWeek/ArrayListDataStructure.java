package FourWeek;

import java.util.ArrayList;

public class ArrayListDataStructure<T> {

    private final int MAX_DATA_LENGTH = 100;
    private Object[] listData = new Object[MAX_DATA_LENGTH];
    private int numOfData;
    private int curPosition;

    public Object[] getListData (){
        return listData;
    }
    public ArrayListDataStructure() {
        this.numOfData = 0;
        this.curPosition = -1;
    }

    public boolean insertFirst(T num) {
        return insert(0, num);
    }

    public boolean insert(T num) {
        if (numOfData >= MAX_DATA_LENGTH) {
            System.out.println("리스트 배열 저장 공간이 부족합니다.");
            return false;
        }
        listData[numOfData] = num;
        numOfData++;
        return true;
    }

    public boolean insert(int index, T num) {

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
        T removed = (T) listData[index - 1];

        for (int i = index + 1; i <= numOfData - 1; i++) {
            listData[i - 1] = listData[i];
        }

        numOfData--;
        listData[numOfData] = 0;

        ArrayList list = new ArrayList();

        return removed;
    }

    public int size() {
        return numOfData;
    }

    public T get(int index) {
        checkIndexRange(index);
        return (T) listData[index - 1];
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

        return new IteratorDataStructure<T>();
    }


    class IteratorDataStructure<T> {

        private int nextIndex = 0;
        private int previousIndex = numOfData;

        public T next() {
            return (T) listData[nextIndex++];
        }

        public boolean hasNext() {
            return nextIndex < size();
        }

        public T previous() {
            if (previousIndex <= 0)
                throw new IndexOutOfBoundsException("인덱스 범위를 벗어났습니다.");
            return (T) listData[--previousIndex];
        }

        public boolean hasPrevious(){
            return previousIndex > 0;
        }

        public T remove(){
            return (T) ArrayListDataStructure.this.remove(nextIndex);
        }

        public void add(T element) {

//            insert(nextIndex++, element);
        }
    }
}
