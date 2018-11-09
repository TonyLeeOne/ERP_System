package com.tony.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
public class IndexController {
    @RequestMapping("index")
    public String index(Map<String, Object> map) {
        map.put("name", "index");
        return "index";
    }
}
