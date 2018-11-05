package com.tony.blog.controller;

import com.tony.blog.entity.Collect;
import com.tony.blog.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/add")
    @ResponseBody
    public String addCollect(String bid){
        Collect collect=new Collect();
        collect.setBid(bid);
        return collectService.insertCollect(collect)>0?"收藏成功":"收藏失败,你已经收藏此博客";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
         collectService.delete(id);
        return "redirect:/user/home";
    }

}
