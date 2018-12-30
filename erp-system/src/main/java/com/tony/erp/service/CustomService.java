package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.CustomMapper;
import com.tony.erp.domain.Custom;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomService {

    @Autowired
    private CustomMapper customMapper;

    public PageHelperEntity getAllCustoms(int pageNum, Custom param) {
        Integer pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        List<Custom> customs = customMapper.getAllCustoms(param);
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(customs);
        pageHelperEntity.setCurrentPage(pageNum);
        PageInfo<Custom> pageInfo = new PageInfo<>(customs);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(), pageSize));
        return pageHelperEntity;
    }

    public int addCustom(Custom custom) {
        custom.setCustomId(KeyGeneratorUtils.keyUUID());
        custom.setCustomStatus(Constant.STRING_ONE);
        return customMapper.insert(custom);
    }

    public int upCustom(Custom custom) {
        return customMapper.updateByPrimaryKey(custom);
    }

    public int delCustom(String cid) {
        return customMapper.deleteByPrimaryKey(cid);
    }

    public Custom getCustom(String code) {
        return customMapper.selectByPrimaryKey(code);
    }

    /**
     * 获取所有有效客户的数量
     *
     * @return
     */
    public int getTotal() {
        return customMapper.getTotal();
    }

    /**
     * 获取所有的客户名及编号
     * @return
     */
    public List<String> getCustoms(){
        return customMapper.getCustoms();
    }


}
