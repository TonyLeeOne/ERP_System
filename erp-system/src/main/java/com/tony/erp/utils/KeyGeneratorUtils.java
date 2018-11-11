package com.tony.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * 主键生成策略及日期转换工具类
 */
public class KeyGeneratorUtils {

    private final static SimpleDateFormat DATE=new SimpleDateFormat("yyyyMMdd",Locale.CHINA);
    private final static SimpleDateFormat TIME=new SimpleDateFormat("yyyyMMddhhmm",Locale.CHINA);
    private final static SimpleDateFormat DATE_GENERATOR=new SimpleDateFormat("yyyy年MM月dd日 a hh时mm分",Locale.CHINA);

    public static  String keyUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static synchronized String keyTime(){
        return TIME.format(new Date());
    }

    public static synchronized String keyDate(){
        return DATE.format(new Date());
    }

    public static synchronized String dateGenerator(){
        return DATE_GENERATOR.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(keyUUID());
    }
}
