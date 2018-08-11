import static jdk.nashorn.internal.objects.Global.print;

public class QuickSort {
    public static void main(String[] args) {
        int[] table = {6,3,2,9,8};
        quickSort(table,0,table.length-1);
    }
    private static void quickSort(int[] table, int low, int high) //一趟快速排序，递归
    {   if (low<high)                //low、high指定序列的下界和上界，序列有效
    {   int i=low, j=high;
        int vot=table[i];                             //第一个值作为枢轴（基准）值
        while (i!=j)                                  //一趟排序
        { while (i<j && vot<=table[j]) {//从后向前寻找较小值
            j--;
        }
            if (i<j)
            {  table[i]=table[j]; i++; }            //较小元素向前移动
            while (i<j && table[i]<vot)               //从前向后寻找较大值
                i++;
            if (i<j)
            {  table[j]=table[i]; j--; }                  //较大元素向后移动
        }
        table[i]=vot;                                  //基准值的最终位置
        print(table);
        quickSort(table, low, j-1);                    //前端子序列再排序
        quickSort(table, i+1, high);                   //后端子序列再排序
    }    }
    public static  void print(int[] table){
        for(int i = 0;i<table.length;i++){
            System.out.print(table[i]);
        }
        System.out.println();
    }
}
