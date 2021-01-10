package FourWeek;

public class LinkedListNodeStack {
    private LinkedListDataStructure head;

    public LinkedListNodeStack() {
        head = new LinkedListDataStructure();
    }

    public Object push(Object element){
        head.addLast(element);
        return element;
    }

    public Object pop(){
        Object data = head.get();
        head.removeLast();
        return data;
    }

    public Object peek(){
        return head.get();
    }

    public int search(Object element){
        int size = head.size();
        int index = size;
        for (int i = 0; i < size; i++){
            if (head.get(i).equals(element)){
                return index;
            }
            index--;
        }
        return -1;
    }

    public boolean empty() {
        return head.size() <= 0;
    }
}
