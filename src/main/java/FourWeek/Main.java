package FourWeek;

public class Main {
    public static void main(String[] args) {
        ArrayListDataStructure<Integer> arrayList = new ArrayListDataStructure<Integer>();

        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);
        arrayList.add(50);

        arrayList.remove(1);
        System.out.println(arrayList);


        ArrayListDataStructure.IteratorDataStructure iterator = arrayList.listIterator();

        while (iterator.hasNext()) {
            int next = (int) iterator.next();
            if (next == 30) {
                iterator.add(31);
                iterator.add("asdf");
            }
        }

        System.out.println(arrayList);
        System.out.println(arrayList.indexOf(40));

        ArrayListDataStructure<String> list = new ArrayListDataStructure<>();
        list.add("테스트");
        list.add("테스트1");
        list.add("테스트2");

        list.addFirst("첫 테스트");
        list.add(1, "두 번째 테스트로 바뀜");

        System.out.println(list);

        ArrayListDataStructure.IteratorDataStructure a = list.listIterator();

        while (a.hasNext()) {
            String number = (String) a.next();
            if (number.equals("테스트")) {
                a.add("테스트합니다!!!!!!!!");
                System.out.println("바로 지우기! " + a.remove());
            }
        }
        System.out.println(list);

        System.out.println("===========================");

        LinkedListDataStructure linkedList = new LinkedListDataStructure();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(50);

        System.out.println(linkedList.contains(10));

        LinkedListDataStructure.ListIterator it = linkedList.listIterator();

        while (it.hasNext()) {
            if ((int) it.next() == 10) {
                it.add(15);
            }
            System.out.println(linkedList);
        }
    }
}
