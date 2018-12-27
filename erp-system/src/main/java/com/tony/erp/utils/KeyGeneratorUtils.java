package com.tony.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 * @author jli2
 * @date  2018/11/12
 * 主键生成策略及日期转换工具类
 */
public class KeyGeneratorUtils {

    private final static SimpleDateFormat DATE=new SimpleDateFormat("yyyyMMdd",Locale.CHINA);
    private final static SimpleDateFormat TIME=new SimpleDateFormat("yyMMddhhmmss",Locale.ENGLISH);
    private final static SimpleDateFormat DATE_GENERATOR=new SimpleDateFormat("yy-MM-dd HH:mm",Locale.CHINA);

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

    public static synchronized String mpKey(){
        return keyTime();
    }

//    public static void main(String[] args) {
//        System.out.println(1+0.02);
//    }

}
