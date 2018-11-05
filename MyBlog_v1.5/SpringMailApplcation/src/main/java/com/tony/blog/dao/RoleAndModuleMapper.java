package com.tony.blog.dao;

import com.tony.blog.entity.RoleAndModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleAndModuleMapper {

    int insert(@Param("list") List<RoleAndModule> list);

    int insertSelective(RoleAndModule record);

    int delete(RoleAndModule roleAndModule);

    List<String> findByRid(String rid);

    int checkExist(RoleAndModule roleAndModule);


}