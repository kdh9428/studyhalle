package FourWeek;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayListDataStructure<Integer> arrayList = new ArrayListDataStructure<Integer>();

        arrayList.insert(10);
        arrayList.insert(20);
        arrayList.insert(30);
        arrayList.insert(50);

        arrayList.remove(1);
        System.out.println(arrayList);



        ArrayListDataStructure.IteratorDataStructure iterator = arrayList.listIterator();

        while (iterator.hasNext()){
            int next = (int) iterator.next();
            if (next == 30){
                iterator.add(31);
                iterator.add("asdf");
            }
        }

        System.out.println(arrayList);
        System.out.println(arrayList.indexOf(40));

        ArrayListDataStructure<String> list = new ArrayListDataStructure<>();
        list.insert("테스트");
        list.insert("테스트1");
        list.insert("테스트2");

        list.insertFirst("첫 테스트");
        list.insert(1, "두 번째 테스트로 바뀜");

        System.out.println(list);

        ArrayListDataStructure.IteratorDataStructure a = list.listIterator();
//
        while (a.hasNext()) {
            String number = (String) a.next();
            if (number.equals("테스트")) {
                a.add("테스트합니다!!!!!!!!");
                System.out.println("바로 지우기! " + a.remove());
            }
        }
        System.out.println(list);

//        while(a.hasPrevious()){
//            System.out.println(a.previous());
//        }
    }
}
