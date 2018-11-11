package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.MaterialConsumeMapper;
import com.tony.erp.dao.MaterialMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialConsume;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialConsumService {

    @Autowired
    private MaterialConsumeMapper materialConsumeMapper;

    @Autowired
    private MaterialService materialService;

    public List<MaterialConsume> getAll(int pageNum){
        PageHelper.startPage(pageNum);
        return materialConsumeMapper.selectAll();
    }

    /**
     * 添加领料记录
     * @param consume
     * @return
     */
    public int addMConsume(MaterialConsume consume){
        consume.setMcId(KeyGeneratorUtils.keyUUID());
        consume.setMcDate(KeyGeneratorUtils.dateGenerator());
        consume.setMcOperator(CurrentUser.getCurrentUser().getUname());
        consume.setMcStatus("1");
//        参数异常-1
        if(StringUtils.isEmpty(consume.getMcMSn())){
            return -1;
        }
        Material material=materialService.checkSnExist(consume.getMcMSn());
        if(ObjectUtils.isEmpty(material)){
            return -1;
        }
//        数量不足，返回-2
        if(material.getmCount()-consume.getMcCountIndeed()<0){
            return -2;
        }
        material.setmCount(material.getmCount()-consume.getMcCountIndeed());
        return materialConsumeMapper.insert(consume)+materialService.upMaterial(material);

    }

    /**
     * 更领料记录
     * @param consume
     * @return
     */
    public int upMConsum(MaterialConsume consume){
        MaterialConsume mc=materialConsumeMapper.selectByPrimaryKey(consume.getMcId());
        if(StringUtils.isEmpty(consume.getMcMSn())){
            return -1;
        }
        Material material=materialService.checkSnExist(consume.getMcMSn());
        if(ObjectUtils.isEmpty(material)){
            return -1;
        }
//        数量不足，返回-2
        if(material.getmCount()-consume.getMcCountIndeed()<0){
            return -2;
        }
        material.setmCount(material.getmCount()+consume.getMcCountIndeed()-mc.getMcCountIndeed());
        return materialConsumeMapper.updateByPrimaryKeySelective(consume)+materialService.upMaterial(material);
    }

    /**
     * 删除领料记录
     * @param mcid
     * @return
     */
    public int delMConsume(String mcid){
        if(StringUtils.isEmpty(mcid)){
            return -1;
        }
        MaterialConsume consume=materialConsumeMapper.selectByPrimaryKey(mcid);
        Material material=materialService.checkSnExist(consume.getMcMSn());
        if("1".equals(consume.getMcStatus())){
            material.setmCount(material.getmCount()+consume.getMcCountIndeed());
            return materialConsumeMapper.deleteByPrimaryKey(mcid)+materialService.upMaterial(material);
        }
        return materialConsumeMapper.deleteByPrimaryKey(mcid);
    }

    public List<MaterialConsume> getByMsn(String msn){
        return materialConsumeMapper.selectByMcMSn(msn);
    }

}
