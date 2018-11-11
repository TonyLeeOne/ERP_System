package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.CustomMapper;
import com.tony.erp.domain.Custom;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CustomService {

    @Autowired
    private CustomMapper customMapper;

    public List<Custom> getAllCustoms(int pageSize) {
        PageHelper.startPage(pageSize, 10);
        return customMapper.getAllCustoms();
    }

    public int addCustom(Custom custom) {
        custom.setCustomId(KeyGeneratorUtils.keyUUID());
        custom.setCustomStatus("1");
        return customMapper.insert(custom);
    }

    public int upCustom(Custom custom) {
        return customMapper.updateByPrimaryKey(custom);
    }

    public int delCustom(String cid) {
        return customMapper.deleteByPrimaryKey(cid);
    }

    public Custom getCustom(String cid) {
        return customMapper.selectByPrimaryKey(cid);
    }


}
