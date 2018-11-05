package com.tony.blog.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Random;

public class Base64Algo {

    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /**
     * 对字符串进行编码
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str)  {
        byte[] bytes=null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoder.encodeToString(bytes);
    }

    /**
     * 对字符串进行解码
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            return new String(decoder.decode(str),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 产生随机字符串
     * @return
     */
    public static String getRandomStr(){
        int i=new Random().nextInt(15)+5;
        char[] chars=new char[i];
        for (int j = 0; j < i; j++) {
            chars[j]=(char)((int)'a'+(int)(Math.random()*26));
        }
        return new String(chars);
    }
}
