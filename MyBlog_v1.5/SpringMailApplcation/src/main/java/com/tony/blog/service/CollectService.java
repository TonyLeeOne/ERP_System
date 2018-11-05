package com.tony.blog.service;

import com.tony.blog.dao.CollectMapper;
import com.tony.blog.entity.Collect;
import com.tony.blog.entity.User;
import com.tony.blog.utils.UUIDUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    @Transactional
    public int insertCollect(Collect collect){
        collect.setId(UUIDUtils.GenerateKey());
        collect.setUid(this.getUser().getUid());
        boolean exist=checkExist(collect);
        if(exist){
            return 0;
        }
        return collectMapper.insertSelective(collect);
    }

    @Transactional
    public int delete(String id){
        return collectMapper.deleteByPrimaryKey(id);
    }

    private User getUser(){
        Subject subject1 = org.apache.shiro.SecurityUtils.getSubject();
        return (User) subject1.getPrincipal();
    }

    public boolean checkExist(Collect collect){
        return collectMapper.checkExist(collect)>0?true:false;
    }

    @Transactional
    public List<Collect> getByUser(String uid){
        return Optional.ofNullable(collectMapper.findByUser(uid)).orElse(null);
    }
}
