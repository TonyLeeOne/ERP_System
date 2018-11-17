package com.tony.erp.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.StorageMapper;
import com.tony.erp.domain.Product;
import com.tony.erp.domain.Storage;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author jli2
 * @date 11/13/2018 9:55 AM
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Autowired
    private ProductService productService;

    /**
     * 获取所有入库记录
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAllStos(int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Storage> stos=storageMapper.find(null);
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(stos);
        PageInfo<Storage> pageInfo=new PageInfo<>(stos);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }

    /**
     * 增加产品入库记录
     * @param storage
     * @return
     */
    public int addStorage(Storage storage){
        storage.setStoId(KeyGeneratorUtils.keyUUID());
        storage.setStoStatus(Constant.STRING_ONE);
        if(ObjectUtils.isEmpty(storage.getStoIndeedNum())||StringUtils.isEmpty(storage.getStoProCode())){
            return Constant.ARG_NOT_MATCHED;
        }
        Product product=productService.getProduct(storage.getStoProCode());
        product.setProCount(product.getProCount()+storage.getStoIndeedNum());
        return productService.upProduct(product)+storageMapper.insertSelective(storage);
    }

    /**
     * 更新进料记录及成品仓库数量
     * @param storage
     * @return
     */
    public int upStorage(Storage storage){
        Storage sto=storageMapper.selectByPrimaryKey(storage.getStoId());
        Product product=productService.getProduct(sto.getStoProCode());
        int delta=product.getProCount()+storage.getStoIndeedNum()-sto.getStoIndeedNum();
        product.setProCount(delta);
        return productService.upProduct(product)+storageMapper.updateByPrimaryKeySelective(storage);
    }

    /**
     * 删除入库记录
     * @param sId
     * @return
     */
    public int delStorage(String sId){
        Storage sto=storageMapper.selectByPrimaryKey(sId);
        if(Constant.STRING_THREE.equals(sto.getStoStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        Product product=productService.getProduct(sto.getStoProCode());
        product.setProCount(product.getProCount()-sto.getStoIndeedNum());
        return productService.upProduct(product)+storageMapper.deleteByPrimaryKey(sId);
    }

    /**
     * 确认入库数量
     * @param sId  主键
     * @param indeed  实际入库数量
     * @param status 确认状态
     * @return
     */
    public int confirm(String sId,int indeed,String status){
        Storage sto=storageMapper.selectByPrimaryKey(sId);
        sto.setStoSurer(CurrentUser.getCurrentUser().getUname());
        sto.setStoRealDate(KeyGeneratorUtils.dateGenerator());
        sto.setStoStatus(status);
        return storageMapper.updateByPrimaryKeySelective(sto);
    }

}
