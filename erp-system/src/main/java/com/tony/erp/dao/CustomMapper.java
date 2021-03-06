package com.tony.erp.dao;

import com.tony.erp.domain.Custom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface CustomMapper {

    int deleteByPrimaryKey(String customId);

    int insert(Custom record);

    int insertSelective(Custom record);

    Custom selectByPrimaryKey(String customId);

    int updateByPrimaryKeySelective(Custom record);

    int updateByPrimaryKey(Custom record);

    List<Custom> getAllCustoms(@Param("offset") int offset,@Param("limit") int limit);

    int getTotal();

    List<String> getCustoms();
}