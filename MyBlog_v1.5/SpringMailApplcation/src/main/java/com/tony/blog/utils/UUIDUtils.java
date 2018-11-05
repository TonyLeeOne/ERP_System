package com.tony.blog.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String GenerateKey(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
