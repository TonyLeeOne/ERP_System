package com.tony.blog.service;

import com.tony.blog.dao.HandUpMapper;
import com.tony.blog.entity.HandUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HandUpsService {

    @Autowired
    private HandUpMapper handUpMapper;


    public Boolean checkExist(HandUp handUp){
        return handUpMapper.findHandsByUidAndBid(handUp)>0;
    }

    public int getHandUp(String bid){
        return handUpMapper.find(bid);
    }

    public int insertHandUp(HandUp handUp){
        boolean exist=this.checkExist(handUp);
        if(!exist){
            handUpMapper.insert(handUp);
        }
        return this.getHandUp(handUp.getBid());
    }

}
