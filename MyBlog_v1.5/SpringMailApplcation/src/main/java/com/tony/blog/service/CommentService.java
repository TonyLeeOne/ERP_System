package com.tony.blog.service;

import com.tony.blog.dao.CommentMapper;
import com.tony.blog.entity.Comment;
import com.tony.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    public List<Comment> getBlogComments(Map<String, String> paras) {
        List<Comment> list = commentMapper.find(paras);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

    @Transactional
    public int insertComment(Comment comment) {
        comment.setId(UUIDUtils.GenerateKey());
        return commentMapper.insert(comment);
    }

    @Transactional
    public List<Comment> getTopicComments(String tid) {

        if (!StringUtils.isEmpty(tid)) {
            return commentMapper.findByTid(tid);
        }

        return null;
    }

    @Transactional
    public int selectiveUpdate(Comment comment) {
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Transactional
    public int deleteByTid(String tid) {
        return commentMapper.deleteByTid(tid);
    }

    @Transactional
    public int deleteByPK(String id) {
        return commentMapper.deleteByPrimaryKey(id);
    }
}
