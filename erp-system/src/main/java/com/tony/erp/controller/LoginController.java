package com.tony.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(Map<String, Object> map) {
        map.put("name", "login");
        return "login";
    }
}
