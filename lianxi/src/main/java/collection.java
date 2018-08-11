import java.util.*;

public class collection {
    static Collection add(Collection c){
        c.add(1);
        c.add(1);
        c.add(2);
        return c;
    }
    static Map add (Map map){
        map.put(1,2);
        map.put(1,3);
        map.put(2,3);
        map.put(3,3);

        return map;
    }
    public static void main(String[] args) {
        System.out.println(add(new HashSet()));
        System.out.println(add(new ArrayList()));
        System.out.println(add(new HashMap()));

    }
}
