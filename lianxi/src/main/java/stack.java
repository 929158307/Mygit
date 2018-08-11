import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class stack {


    public static void main(String[] args) {
        linkedListStack linkedListStack = new linkedListStack();
        int i = 0;
        while (i < 5) {
            int number = new Random().nextInt(10);
            linkedListStack.pull(number);
            System.out.print(number + " ");
            i++;
        }
        System.out.println("");
        System.out.println("----------------------");
        while (linkedListStack.hasNext()) {
            System.out.print(linkedListStack.pop());
        }
    }
    HashMap
}

class linkedListStack {
    LinkedList linkedList = new LinkedList();

    public boolean pull(Object object) {
        linkedList.addFirst(object);
        return true;
    }

    public Object pop() {
        Object object = linkedList.getFirst();
        linkedList.remove(object);
        return object;
    }

    public boolean hasNext() {
        if (linkedList.size() > 0) {
            return true;
        }
        return false;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        linkedListStack that = (linkedListStack) o;

        return linkedList != null ? linkedList.equals(that.linkedList) : that.linkedList == null;
    }

    @Override
    public int hashCode() {
        return linkedList != null ? linkedList.hashCode() : 0;
    }
}