package com.tony.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.*;

@Controller
public class HelloController {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.getTime());
//        List<String> test = new ArrayList<String>();
//        test.add("shang");
//        test.add("hai");
//        System.out.println("------");
//        System.out.println(test);
//        System.out.println("------");
//
//        for(String ele: test){
//            System.out.println(ele);
//        }
//        String[] tst = "hello,world".split(",");
//        List<String> list = Arrays.asList(tst);
//        for (String ele : list) {
//            System.out.println(ele);
//        }
    }

    @RequestMapping("/hello")
    public String index(Map<String, Object> map) {
        map.put("name", "HelloController");
        return "hello";
    }
}
