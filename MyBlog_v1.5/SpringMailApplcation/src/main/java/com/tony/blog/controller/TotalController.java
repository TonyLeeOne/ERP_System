package com.tony.blog.controller;

import com.tony.blog.entity.Blog;
import com.tony.blog.service.BlogService;
import com.tony.blog.service.ShiroConfigureService;
import com.tony.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TotalController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ShiroConfigureService shiroConfigureService;

    @RequestMapping("/index/blog/{page}")
    @ResponseBody
    public List<Blog> getAllBlogs(ModelMap modelMap, @PathVariable int page){
        return blogService.getAllBlogsByPage(page);
    }

    @RequestMapping("/index")
    public String getAllBlogs(ModelMap modelMap){
        modelMap.addAttribute("blogs",blogService.getAllBlogsByPage(1));
        modelMap.addAttribute("tags",blogService.getAllTags());
        return "index";
    }


    @RequestMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @RequestMapping("/question")
    public String question(ModelMap modelMap){
        modelMap.addAttribute("tags",blogService.getAllTags());
        modelMap.addAttribute("resolved",topicService.getByStatus("1"));
        modelMap.addAttribute("unresolved",topicService.getByStatus("0"));
        return "question";
    }


}
