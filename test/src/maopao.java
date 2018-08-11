import java.util.Random;

public class maopao {
    public static void main(String[] args) {
        //建数组
        Integer[] integers = new Integer[10];
        for(int i = 0;i<integers.length;i++){
            Random random = new Random();
            integers[i] = random.nextInt(20);
        }
        //遍历
        Integer[] maopao = maopao(integers);
        for(int i =0;i<maopao.length;i++){
            System.out.print(maopao[i] + " ");
        }
    }
    //冒泡方法
    public static Integer[] maopao(Integer[] integers){
        for(int i = 0;i<integers.length - 1;i++){
            for(int j = 0;j<integers.length - i -1;j++){
                if(integers[j] > integers[j+1]){
                    int c = integers[j];
                    integers[j] = integers[j+1];
                    integers[j+1] = c;
                }

            }
        }
        return integers;
    }
}
