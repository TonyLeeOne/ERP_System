package com.tony.erp.service;

import com.tony.erp.dao.BomDetailMapper;
import com.tony.erp.domain.BomDetail;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jli2
 * @date 12/12/2018 5:47 PM
 **/
@Service
public class DetailService {

    @Autowired
    private BomDetailMapper mapper;

    /**
     * 根据bcode查找所有的物料清单信息
     * @param bCode
     * @return
     */
    public List<BomDetail> selectByBCode(String bCode){
        return mapper.find(bCode);
    }

    /**
     * 根据主键查找BOM清单
     * @param bdId
     * @return
     */
    public BomDetail selectByPrimaryKey(String bdId){
        return mapper.selectByPrimaryKey(bdId);
    }

    /**
     * 添加新清单信息
     * @param bomDetail
     * @return
     */
    public int add(BomDetail bomDetail){
        bomDetail.setBdId(KeyGeneratorUtils.keyUUID());
        return mapper.insertSelective(bomDetail);
    }

    /**
     * 修改物料信息
     * @param bomDetail
     * @return
     */
    public int update(BomDetail bomDetail){
        return mapper.updateByPrimaryKeySelective(bomDetail);
    }

    /**
     * 删除清单记录
     * @param bdId
     * @return
     */
    public int delete(String bdId){
        return mapper.deleteByPrimaryKey(bdId);
    }
}
