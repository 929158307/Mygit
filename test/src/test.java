import java.util.HashMap;
import java.util.Set;

public class test {
    public static void main(String[] args) {
        Integer[] num = new Integer[]{1, 2, 2, 2, 3, 3, 4, 4};
        HashMap hashMap = find2(num);
        Set set = hashMap.keySet();
        System.out.println(set);

        for (Object object : set) {
            if ((int) hashMap.get(object) == 2) {
                System.out.println(object + "");
            }

        }
    }
    public static HashMap<Integer,Integer>  find2(Integer[] num){
        HashMap map = new HashMap<Integer,Integer>();
        for(int i = 0;i<num.length;i++) {
            if (map.containsKey(num[i])) {
                int time = (int) map.get(num[i]);
                map.put(num[i], ++time);
            } else {
                map.put(num[i],1);
            }
        }
        return map;
    }
}
