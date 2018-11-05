package com.tony.blog.controller;

import com.tony.blog.entity.Topic;
import com.tony.blog.service.CommentService;
import com.tony.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private TopicService topicService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/add")
    public String add(String subject, String category, String content) {
        Topic topic = new Topic();
        topic.setSubject(subject);
        topic.setContent(content);
        topic.setCategory(category);
        topicService.insertTopic(topic);
        return "redirect:/question";
    }

    @RequestMapping("/query/{id}")
    public String queryById(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("topic", topicService.selectById(id));
        modelMap.addAttribute("comments", commentService.getTopicComments(id));
        return "questionDetail";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        if(!StringUtils.isEmpty(id)){
            topicService.delete(id);
            commentService.deleteByTid(id);
        }
        return "redirect:/question";
    }

    @RequestMapping("/comment/delete/{id}/{tid}")
    public String deleteComment(@PathVariable String id,@PathVariable String tid) {
        if(!StringUtils.isEmpty(id)&&!StringUtils.isEmpty(tid)){
           commentService.deleteByPK(id);
        }
        return "redirect:/topic/query/"+tid;
    }


    @RequestMapping("/findTags")
    @ResponseBody
    public List<String> findTags(){
        return topicService.findAllTags();
    }

    @PostMapping("/findTopicsByTag")
    @ResponseBody
    public List<Topic> findTopicsByTag(String tag){
        if(!StringUtils.isEmpty(tag)){
            Map<String,String> map=new HashMap<>();
            map.put("category",tag);
            return topicService.find(map);
        }
        return null;

    }

}
