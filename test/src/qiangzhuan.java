public class qiangzhuan {
    public static void main(String[] args) {
        String str = "+323a";
        Integer qiangzhuan = qiangzhuan(str);
        System.out.println(qiangzhuan);
    }
    public static  Integer qiangzhuan(String string){
        int num = 0;
        char[] chars = string.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if('0' <= chars[i] && '9' >= chars[i]){
                num += (chars[i]-48)  * (Math.pow(10,chars.length- i -1));
            }
            else {
                throw new NumberFormatException("这个不是数字");
            }
        }
        return  num;

    }
}
