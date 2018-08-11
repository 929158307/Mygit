public class kuaipai {
    public static void main(String[] args) {
        int[] table = {6,3,2,9,8};
        quickSort(table,0,table.length-1);
    }
    public static void quickSort(int[]table,int low,int high){
        if(low<high){
            int i = low;
            int j = high;
            int num = table[i];
            while(i!=j){
                while(i<j && num<=table[j]){
                    j--;
                }
                if(i<j){
                    table[i] = table[j];
                    i++;
                }
                while(i<j && table[i] < num){
                    i++;
                }
                if(i<j){
                    table[j] = table[i];
                    j--;
                }
            }
            table[i] = num;
            print(table);
            quickSort(table,low,j-1);
            quickSort(table,i+1,high);
        }
    }
    public static  void print(int[] table){
        for(int i = 0;i<table.length;i++){
            System.out.print(table[i]);
        }
        System.out.println();
    }
}
