package com.tony.blog.controller;

import com.tony.blog.entity.HandUp;
import com.tony.blog.entity.User;
import com.tony.blog.service.HandUpsService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/handup")
public class HandUpController {

    @Autowired
    private HandUpsService service;

    @PostMapping("/add")
    @ResponseBody
    public int insert(String bid){
        HandUp handUp=new HandUp();
        handUp.setBid(bid);
        handUp.setUid(getUser().getUid());
        return service.insertHandUp(handUp);
    }


    @PostMapping("/query")
    @ResponseBody
    public int getHands(String bid){
        return service.getHandUp(bid);
    }

    private User getUser(){
        Subject subject1 = org.apache.shiro.SecurityUtils.getSubject();
        return (User) subject1.getPrincipal();
    }
}
