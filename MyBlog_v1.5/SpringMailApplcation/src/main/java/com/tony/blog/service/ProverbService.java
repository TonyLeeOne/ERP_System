package com.tony.blog.service;

import com.tony.blog.dao.ProverbMapper;
import com.tony.blog.entity.Proverb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProverbService {

    @Autowired
    private ProverbMapper proverbMapper;

    @Transactional
    public List<Proverb> getAllProverbs(){
        return proverbMapper.findAllProverbs().isEmpty()?null:proverbMapper.findAllProverbs();
    }

    @Transactional
    public int addProverb(Proverb proverb){
        return proverbMapper.insert(proverb);
    }

    @Transactional
    public int updateProverb(Proverb proverb){
        return proverbMapper.updateByPrimaryKey(proverb);
    }

    @Transactional
    public int deleteProverb(String id){
        return proverbMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public Proverb findProverbById(String id){
        return proverbMapper.selectByPrimaryKey(id);
    }

}
