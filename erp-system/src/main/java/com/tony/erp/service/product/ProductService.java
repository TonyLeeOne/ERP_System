package com.tony.erp.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.ProductMapper;
import com.tony.erp.dao.ProfileMapper;
import com.tony.erp.domain.Product;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public PageHelperEntity getAllProducts(int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Product> products=productMapper.getAllProducts();
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(products);
        PageInfo<Product> pageInfo=new PageInfo<>(products);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }

    public int addProduct(Product product){
        if(StringUtils.isEmpty(product.getProCode())){
            return Constant.ARG_NOT_MATCHED;
        }
        Product p=productMapper.selectByProCode(product.getProCode());
        if(!ObjectUtils.isEmpty(p)){
         return Constant.STATUS_CANNOT_CHANGED;
        }
        product.setProId(KeyGeneratorUtils.keyUUID());
        product.setProStatus(Constant.STRING_ONE);
        return productMapper.insert(product);
    }

    public int upProduct(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public int delProduct(String pid){
        return productMapper.deleteByPrimaryKey(pid);
    }

    public Product getProduct(String proCode){
        return productMapper.selectByProCode(proCode);
    }

}
