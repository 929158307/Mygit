import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class jiaoji {
    public static void main(String[] args) {
        person person2 = new person("小勇",3);
        person person = new person("小明",2);
        person person1 = new person("小刚",3);
        TreeSet<person> treeSet = new TreeSet<>();
        treeSet.add(person1);
        treeSet.add(person);
        treeSet.add(person2);
        System.out.println(treeSet);
    }

}
class person implements Comparable{

    @Override
    public int compareTo(Object o) {
        person person = (person) o;
        int result = this.age>person.age?1:(this.age ==  person.age?0:-1);
        if(result == 0){
            result = this.name.compareTo(person.name);
        }
        return result;
    }
    String name;
    int age;

    public person() {
    }

    public person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return "姓名："+name+",年龄："+age;
    }
}
