package com.tony.erp.service;

import com.tony.erp.constant.Constant;
import com.tony.erp.dao.BOMMapper;
import com.tony.erp.domain.BOM;
import com.tony.erp.domain.BomDetail;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author jli2
 * @date 12/12/2018 2:11 PM
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class BomService {

    @Autowired
    private BOMMapper bomMapper;

    @Autowired
    private DetailService detailService;

    public BOM getByPrimaryKey(String bId){
        return bomMapper.selectByPrimaryKey(bId);
    }

    public int addBom(BOM bom){
        bom.setBId(KeyGeneratorUtils.keyUUID());
        return bomMapper.insertSelective(bom);
    }

    public int update(BOM bom){
        return bomMapper.updateByPrimaryKeySelective(bom);
    }

    public List<BOM> findAll(){
        return bomMapper.find(null);
    }

    public BOM selectByBCode(String bCode){
        return Optional.ofNullable(bomMapper.find(bCode).get(0)).orElse(null);
    }


    public BOM selectByPCode(String bpCode){
        return Optional.ofNullable(bomMapper.selectByPCode(bpCode)).orElse(null);
    }

    /**
     * 删除bom及其清单
     * @param bId
     * @return
     */
    public int delete(String bId){
        BOM bom=this.getByPrimaryKey(bId);
        if(CollectionUtils.isEmpty(bom.getDetails())){
            return bomMapper.deleteByPrimaryKey(bId);
        }
        Set<BomDetail> details=bom.getDetails();
        for (BomDetail detail:details) {
            if(detailService.delete(detail.getBdId())<0){
                return Constant.ARG_NOT_MATCHED;
            }
        }
        return bomMapper.deleteByPrimaryKey(bId);
    }

    /**
     * 检查bCode是否存在
     * @param bCode
     * @return
     */
    public boolean checkBCodeExist(String bCode){
        return bomMapper.checkExistByBCode(bCode)>0?true:false;
    }
}
