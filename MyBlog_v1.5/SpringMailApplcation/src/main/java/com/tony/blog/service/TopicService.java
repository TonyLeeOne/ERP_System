package com.tony.blog.service;

import com.tony.blog.dao.TopicMapper;
import com.tony.blog.entity.Topic;
import com.tony.blog.utils.TimeUtils;
import com.tony.blog.utils.UUIDUtils;
import com.tony.blog.utils.UserInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicMapper topicMapper;

    public int insertTopic(Topic topic){
        topic.setId(UUIDUtils.GenerateKey());
        topic.setUid(UserInfoUtils.getUser().getUid());
        topic.setCtime(TimeUtils.getTime());
        topic.setStatus("0");
        return topicMapper.insert(topic);
    }

    public int update(Topic topic){
        return topicMapper.updateByPrimaryKey(topic);
    }

    public List<Topic> getByStatus(String status){
        return Optional.ofNullable(topicMapper.selectByStatus(status)).orElse(null);
    }

    public List<Topic> find(Map<String,String> paras){
        List<Topic> list= topicMapper.find(paras);
        return Optional.ofNullable(list).orElse(null);
    }

    public Topic selectById(String id){
        return Optional.ofNullable(topicMapper.selectByPrimaryKey(id)).orElse(null);
    }

    public int updateSelective(Topic topic){
        return topicMapper.updateByPrimaryKeySelective(topic);
    }


    public int delete(String id){
        return topicMapper.deleteByPrimaryKey(id);
    }


    public List<String> findAllTags(){
        return Optional.ofNullable(topicMapper.findTags()).orElse(null);
    }
}
