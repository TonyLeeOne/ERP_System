package com.tony.erp.dao;

import com.tony.erp.domain.RoleModule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface RoleModuleMapper {

    int insert(@Param("list")List<RoleModule> list);

    int insertSelective(RoleModule record);

    int delete(RoleModule roleModule);

    List<String> selectMidByRid(@Param("rid") String rid);

    int deleteByRid(@Param("rid") String rid);
}