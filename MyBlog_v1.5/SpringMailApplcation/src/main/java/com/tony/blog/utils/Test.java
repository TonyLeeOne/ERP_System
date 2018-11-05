package com.tony.blog.utils;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        String path="/WEB-INF/static/defImages/";
        File f=new File(path);
        if(f.exists()&&f.isDirectory()){
            System.out.println("-------");
            for (File file:f.listFiles()
                 ) {
                if(file.isFile()){
                    System.out.println(file.getPath());
                }
            }
        }
    }
}
