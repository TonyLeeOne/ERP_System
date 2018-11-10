package com.tony.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * 主键生成策略及日期转换工具类
 */
public class KeyGeneratorUtils {

    private final static SimpleDateFormat date=new SimpleDateFormat("yyyyMMdd",Locale.CHINA);
    private final static SimpleDateFormat time=new SimpleDateFormat("yyyyMMddhhmm",Locale.CHINA);
    private final static SimpleDateFormat dateGenerator=new SimpleDateFormat("yyyy年MM月dd日 a hh时mm分",Locale.CHINA);

    public static String keyUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String keyTime(){
        return time.format(new Date());
    }

    public static String keyDate(){
        return date.format(new Date());
    }

    public static String dateGenerator(){
        return dateGenerator.format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(keyUUID());
    }
}
