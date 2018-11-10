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
public class CustomService {

    @Autowired
    private CustomMapper customMapper;

    @Transactional
    public List<Custom> getAllCustoms(int pageSize) {
        PageHelper.startPage(pageSize, 10);
        return customMapper.getAllCustoms();
    }

    @Transactional
    public int addCustom(Custom custom) {
        custom.setCustomId(KeyGeneratorUtils.keyUUID());
        custom.setCustomStatus("1");
        return customMapper.insert(custom);
    }

    @Transactional
    public int upCustom(Custom custom) {
        return customMapper.updateByPrimaryKey(custom);
    }

    @Transactional
    public int delCustom(String c_id) {
        return customMapper.deleteByPrimaryKey(c_id);
    }

    @Transactional
    public Custom getCustom(String c_id) {
        return customMapper.selectByPrimaryKey(c_id);
    }


}
