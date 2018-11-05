package com.tony.blog.controller;

import com.tony.blog.entity.Blog;
import com.tony.blog.entity.User;
import com.tony.blog.service.BlogService;
import com.tony.blog.service.CaffeineService;
import com.tony.blog.service.CommentService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/blog")
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private CaffeineService caffeineService;



    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm a",Locale.CHINESE);

    /**
     * 写博客
     * @param modelMap
     * @return
     */
    @RequestMapping("/add")
    public String home(ModelMap modelMap){
        return "writeBlog";
    }

    @RequestMapping("/save")
    public String save(String subject,String content,String tag){
        Blog blog=new Blog();
        blog.setUid(this.getUser().getUid());
        blog.setSubject(subject);
        blog.setContents(content);
        blog.setPtime(sdf.format(new Date()));
        blog.setTag(tag);
        blogService.save(blog);
        return "redirect:/user/home";
    }

    @RequestMapping("/edit/{id}")
    public String update(@PathVariable String id,ModelMap modelMap){
        modelMap.addAttribute("blog",blogService.getBlogById(id));
        return "editBlog";
    }

    @RequestMapping("/browse/{id}")
    public String browse(@PathVariable String id,ModelMap modelMap){
        Map<String,String> map=new HashMap<>();
        map.put("bid",id);
        modelMap.addAttribute("blog",blogService.getBlogById(id));
        modelMap.addAttribute("comment",commentService.getBlogComments(map));
        return "browseBlog";
    }


    @RequestMapping("/update")
    public String update(String bid,String subject,String tag,String content){
        Blog blog=blogService.getBlogById(bid);
        if(!ObjectUtils.isEmpty(blog)){
            blog.setTag(tag);
            blog.setContents(content);
            blog.setSubject(subject);
            blog.setUid(this.getUser().getUid());
            blog.setUtime(sdf.format(new Date()));
            blogService.updateBlog(blog);
        }
        return "redirect:/user/home";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        if(!ObjectUtils.isEmpty(id)){
            blogService.deleteBlog(id);
        }
        return "redirect:/user/home";
    }

    @RequestMapping("/search/{tag}")
    public String getBlogsByTag(@PathVariable String tag,ModelMap modelMap){
        Map<String,Object> paras=new HashMap<>();
        paras.put("tag",tag);
        paras.put("uid",this.getUser().getUid());
        modelMap.addAttribute("blogs",blogService.getBlogsByUserTag(paras));
        modelMap.addAttribute("tags",blogService.getTags(this.getUser().getUid()));
        return "userHome";
    }

    @RequestMapping("/{tag}")
    public String getBlogsByTags(@PathVariable String tag,ModelMap modelMap){
        modelMap.addAttribute("blogs",blogService.getBlogsByTag(tag));
        modelMap.addAttribute("tags",blogService.getAllTags());
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "",method = RequestMethod.POST)
    public List<Blog> getAllBlog(){
        return caffeineService.getAllBlogs();
    }

    private User getUser(){
        Subject subject1 = org.apache.shiro.SecurityUtils.getSubject();
        return (User) subject1.getPrincipal();
    }
}
