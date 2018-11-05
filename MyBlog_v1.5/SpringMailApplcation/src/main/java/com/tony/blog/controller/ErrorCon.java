package com.tony.blog.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorCon implements ErrorController {

    private static final String ERROR_PATH="/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public String handleErrorPath(){
        return "404";
    }
}
