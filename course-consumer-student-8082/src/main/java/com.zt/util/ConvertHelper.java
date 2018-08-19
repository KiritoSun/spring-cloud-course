package com.zt.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

public class ConvertHelper {
    private static Map<String,String> map;

    static {
        map = new HashMap<>();
        map.put("星期一","1");
        map.put("星期二","2");
        map.put("星期三","3");
        map.put("星期四","4");
        map.put("星期五","5");
    }

    public static String concert(String str){
        String s = "";
        try{
            String[] as = str.split(" ");
            s = map.get(as[0]);
            s+="-"+as[1];
        }catch (Exception e){
            e.printStackTrace();
        }finally {}
        return s;
    }
}
