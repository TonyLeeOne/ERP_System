package com.tony.erp.service;

import com.tony.erp.dao.ProductMapper;
import com.tony.erp.dao.ProfileMapper;
import com.tony.erp.domain.Product;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public List<Product> getAllProducts(){
        return productMapper.getAllProducts();
    }
    @Transactional
    public int addProduct(Product product){
        product.setProId(KeyGeneratorUtils.keyUUID());
        product.setProStatus("1");
        return productMapper.insert(product);
    }
    @Transactional
    public int upProduct(Product product){
        return productMapper.updateByPrimaryKeySelective(product);
    }
    @Transactional
    public int delProduct(String pid){
        return productMapper.deleteByPrimaryKey(pid);
    }
    @Transactional
    public Product getProduct(String pid){
        return productMapper.selectByPrimaryKey(pid);
    }

}
