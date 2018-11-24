package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.dao.CustomMapper;
import com.tony.erp.domain.Custom;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomService {

    @Autowired
    private CustomMapper customMapper;

<<<<<<< HEAD
    public PageHelperEntity getAllCustoms(int pageSize) {
        List<Custom> customs=customMapper.getAllCustoms();
        PageHelper.startPage(pageSize, 10);
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
=======
    public PageHelperEntity getAllCustoms(int pageNum, int pageSize) {
        List<Custom> customs = customMapper.getAllCustoms(pageNum,pageSize);
        PageHelper.startPage(pageNum, pageSize);
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
>>>>>>> 74567b02b01685cb5748adfaf9b79817fd9458bc
        pageHelperEntity.setRows(customs);
        PageInfo<Custom> pageInfo = new PageInfo<>(customs);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(), pageSize));
        return pageHelperEntity;
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

    /**
     * 获取所有有效客户的数量
     *
     * @return
     */
    public int getTotal() {
        return customMapper.getTotal();
    }


}
