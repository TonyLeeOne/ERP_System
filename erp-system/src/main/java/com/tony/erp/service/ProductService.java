package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.ProductMapper;
import com.tony.erp.dao.ProfileMapper;
import com.tony.erp.domain.Product;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getAllProducts(int pageNum){
        PageHelper.startPage(pageNum,10);
        return productMapper.getAllProducts();
    }

    public int addProduct(Product product){
        product.setProId(KeyGeneratorUtils.keyUUID());
        product.setProStatus("1");
        return productMapper.insert(product);
    }

    public int upProduct(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }

    public int delProduct(String pid){
        return productMapper.deleteByPrimaryKey(pid);
    }

    public Product getProduct(String pid){
        return productMapper.selectByPrimaryKey(pid);
    }

}
