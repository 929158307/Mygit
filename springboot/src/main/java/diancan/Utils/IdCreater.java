package diancan.Utils;

import java.util.Random;

public class IdCreater {
    public static synchronized String creater(){
        Integer number = new Random().nextInt(900000)+100000;
        Long time = System.currentTimeMillis();
        return String.valueOf(number) + time;
    }
}
