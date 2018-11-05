package com.tony.blog.service;

import com.github.pagehelper.PageHelper;
import com.tony.blog.dao.BlogMapper;
import com.tony.blog.entity.Blog;
import com.tony.blog.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class BlogService {

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CaffeineService service;

    /**
     * 插入blog
     */

    @Transactional
    public int save(Blog blog) {
        blog.setId(UUIDUtils.GenerateKey());
        return blogMapper.insert(blog);
    }

    /**
     * 根据用户id查找所有博客
     *
     * @param uid
     * @return
     */
    @Transactional
    public List<Blog> getBlogsOfuser(String uid) {
        return Optional.ofNullable(blogMapper.findBlogsByUid(uid)).orElse(null);
    }

    @Transactional
    public Blog getBlogById(String bid) {
        return Optional.ofNullable(blogMapper.selectByPrimaryKey(bid)).orElse(null);
    }

    @Transactional
    public int updateBlog(Blog blog) {
        return blogMapper.updateByPrimaryKeySelective(blog);
    }

    @Transactional
    public int deleteBlog(String id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    public List<Blog> getAllBlogsByPage(int page) {
        PageHelper.startPage(page, pageSize);
        return blogMapper.getAllBlogs();
    }

    @Transactional
    public List<Blog> getAllBlogs() {
        return service.getAllBlogs();
    }

    @Transactional
    public List<String> getTags(String uid) {
        List<String> list = new ArrayList<>();
        List<Blog> blogs = this.getBlogsOfuser(uid);
        if (!CollectionUtils.isEmpty(blogs)) {
            for (Blog blog : blogs) {
                list.add(blog.getTag());
            }
            return listUtils(list);
        }

        return null;

    }

    /**
     * List去重复值
     *
     * @param list
     * @return
     */
    private List<String> listUtils(List<String> list) {
        if (!list.isEmpty()) {
            Set set = new HashSet(list);
            list.clear();
            list.addAll(set);
        }
        return list;
    }

    @Transactional
    public List<Blog> getBlogsByUserTag(Map<String,Object> params) {
        return blogMapper.findBlogsByUserTag(params);
    }

    @Transactional
    public List<Blog> getBlogsByTag(String tag) {
        return blogMapper.findBlogsByTag(tag);
    }


    @Transactional
    public List<String> getAllTags() {
        List<String> list = new ArrayList<>();
        List<Blog> blogs = this.getAllBlogs();
        if (!CollectionUtils.isEmpty(blogs)) {
            for (Blog blog : blogs) {
                list.add(blog.getTag());
            }
            return listUtils(list);
        }

        return null;
    }
}
