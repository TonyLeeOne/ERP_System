package com.tony.blog.dao;

import com.tony.blog.entity.HandUp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HandUpMapper {
    int insert(HandUp record);
    int insertSelective(HandUp record);
    int findHandsByUidAndBid(HandUp record);
    int find(@Param("bid") String bid);
}