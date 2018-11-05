package com.tony.blog.dao;

import com.tony.blog.entity.Module;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleMapper {
    List<Module> selectAll();
}