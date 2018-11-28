package com.tony.erp.dao;

import com.tony.erp.domain.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface ProductMapper {

    int deleteByPrimaryKey(String proId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByProCode(String proCode);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> getAllProducts();

    int getTotal();

    int batchDeleteByProCode(@Param("proCodes")String[] proCodes);

    List<String> selectProCodes();
}