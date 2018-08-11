import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class copy {
    public static void main(String[] args) {
//        int[] ints = {1,2,3,4,5};
//        int[] ints2 = new int[ints.length+10];
//        System.arraycopy(ints,0,ints2,3,3);
//        for(Integer integer : ints){
//            System.out.print(integer);
//        }
//        System.out.println("");
//        System.out.println("-----------------------");
//        for(Integer integer : ints2){
//            System.out.print(integer);
//        }
//        System.out.println("");
//        System.out.println(ints.equals(ints2));
//        A a = new A();
//        A b = new A();
//        System.out.println(a==b);
//        System.out.println(a.equals(b));

        //数组
//        int[] ints = new int[10];
//       for(int i = 0;i<ints.length;i++){
//           ints[i] = new Random().nextInt(100);
//       }
//       for(Integer integer : ints){
//           System.out.print(integer+",");
//       }
//        Arrays.sort(ints);
//        System.out.println();
//        System.out.print("-----------------");
//        System.out.println();
//        for(Integer integer : ints){
//            System.out.print(integer+",");
//        }
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
        Set s = new HashSet();
        s.add(4);
        s.add("4");
        System.out.println(s);

    }
}
class A{
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
