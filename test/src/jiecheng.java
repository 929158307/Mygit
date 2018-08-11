public class jiecheng {
    public static void main(String[] args) {
        System.out.println(jiecheng(4));
    }
    public static int jiecheng(int i){
        if(i < 2){
            return 1;
        }else {
            return i*jiecheng(i-1);
        }
    }
}
