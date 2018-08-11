import java.util.ArrayList;
import java.util.List;

public class testtwo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> end = num(list);
        System.out.println(end);
    }
    public static ArrayList<Integer> num(ArrayList<Integer> list){
        if(list.size()>12){
            return list;
        }
        if(2>= list.size()){
            list.add(1);
        }
        else {
            int number = list.get(list.size()-1) + list.get(list.size()-2);
            list.add(number);
        }
        return num(list);
    }
}
