package com.tony.erp.service;

import com.tony.erp.dao.ProfileMapper;
import com.tony.erp.domain.Profile;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProfileService {

    @Autowired
    private ProfileMapper profileMapper;

    /**
     * 添加用户资料
     * @param profile
     * @return
     */
    public int addProfile(Profile profile){
        profile.setPid(KeyGeneratorUtils.keyUUID());
        return profileMapper.insertSelective(profile);
    }

    /**
     * 更新用户资料
     * @param profile
     * @return
     */
    public int upProfile(Profile profile){
        return profileMapper.updateByPrimaryKeySelective(profile);
    }
}
