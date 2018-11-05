package com.tony.blog.service;

import com.tony.blog.dao.ProfileMapper;
import com.tony.blog.entity.Profile;
import com.tony.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileMapper profileMapper;


    /**
     * 插入新的会员资料
     * @param profile
     * @return
     */
    public int insertProfile(Profile profile){
        profile.setId(UUIDUtils.GenerateKey());
        return profileMapper.insert(profile);
    }

    /**
     * 更新会员资料
     * @param profile
     * @return
     */
    public int update(Profile profile){
        return profileMapper.updateByPrimaryKeySelective(profile);
    }

    /**
     *
     * @param uid
     * @return
     */
    public Profile get(String uid){
        return Optional.ofNullable(profileMapper.selectByPrimaryKey(uid)).orElse(null);
    }
}
