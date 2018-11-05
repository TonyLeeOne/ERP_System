package com.tony.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {

    public static String getTime(){
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm a",Locale.CHINESE).format(new Date());
    }
}
