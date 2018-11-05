package com.tony.blog.dao;

import com.tony.blog.entity.Proverb;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProverbMapper {
    /**
     * 根据主键删除proverb
     * 
     */
    int deleteByPrimaryKey(String id);

    /**
     *插入一条新的proverb
     *
     * 
     */
    int insert(Proverb record);

    /**
     *插入一条新的proverb
     * 
     */
    int insertSelective(Proverb record);

    /**
     * 根据ID查询proverb
     *
     * 
     */
    Proverb selectByPrimaryKey(String id);

    /**
     *
     *更新proverb
     * 
     */
    int updateByPrimaryKeySelective(Proverb record);

    /**
     * 根据主键更新proverb
     *
     * 
     */
    int updateByPrimaryKey(Proverb record);

    /**
     * 查找所有的proverbs
     * @return
     */
    List<Proverb> findAllProverbs();
}