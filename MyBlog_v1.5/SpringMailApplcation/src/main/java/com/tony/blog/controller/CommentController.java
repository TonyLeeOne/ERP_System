package com.tony.blog.controller;

import com.tony.blog.entity.Comment;
import com.tony.blog.entity.Topic;
import com.tony.blog.entity.User;
import com.tony.blog.service.CommentService;
import com.tony.blog.service.TopicService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/comment")
public class CommentController {

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日 HH:mm a",Locale.CHINESE);

    @Autowired
    private CommentService commentService;

    @Autowired
    private TopicService topicService;

    @RequestMapping("/add")
    public String addComment(String bid,String content){
        User user=this.getUser();
        Comment comment=new Comment();
        comment.setBid(bid);
        comment.setContent(content);
        comment.setUid(user.getUid());
        comment.setCtime(sdf.format(new Date()));
        commentService.insertComment(comment);
        return "redirect:/blog/browse/"+bid;
    }


    private User getUser(){
        Subject subject1 = org.apache.shiro.SecurityUtils.getSubject();
        return (User) subject1.getPrincipal();
    }

    @RequestMapping("/addT")
    public String addTComment(String tid,String content){
        if(!StringUtils.isEmpty(tid)&&!StringUtils.isEmpty(content)){
            Comment comment=new Comment();
            User user=this.getUser();
            comment.setTid(tid);
            comment.setContent(content);
            comment.setUid(user.getUid());
            comment.setCtime(sdf.format(new Date()));
            commentService.insertComment(comment);
        }
        return "redirect:/topic/query/"+tid;
    }

    @RequestMapping("/update/{id}/{tid}")
    public String update(@PathVariable String id,@PathVariable String tid){
        if(!StringUtils.isEmpty(id)&&!StringUtils.isEmpty(tid)){
            Comment comment=new Comment();
            comment.setId(id);
            comment.setStatus("1");

            Topic topic=new Topic();
            topic.setId(tid);
            topic.setStatus("1");
            commentService.selectiveUpdate(comment);
            topicService.updateSelective(topic);
        }
        return "redirect:/topic/query/"+tid;
    }

}
