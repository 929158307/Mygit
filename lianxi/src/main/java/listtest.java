import java.util.*;

public class listtest {
    public static void main(String[] args) {
        List list = new ArrayList();
        int i = 1;
        String a ="haha";
        String b ="1";
        String c ="1";
        list.add(i);
        list.add(a);
        System.out.println(list);
        Set set = new HashSet();
        set.add(i);
        set.add(a);
        set.add(b);
        set.add(c);
        System.out.println(set);
        Map map = new HashMap();
        map.put(null,2);
        Set set1 = map.keySet();
        for(Object key: map.keySet()){
            System.out.println(map.get(key));
        }
        Map<String,String> map1 = new HashMap<String, String>();
        map1.put("2","3");
        for(Map.Entry<String, String> entry :map1.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }
}
