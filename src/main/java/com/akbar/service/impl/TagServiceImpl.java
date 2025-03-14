package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.constant.MessageConstant;
import com.akbar.exception.DeletionNotAllowedException;
import com.akbar.mapper.ArticleMapper;
import com.akbar.mapper.ArticleTagMapper;
import com.akbar.pojo.entity.Tag;
import com.akbar.mapper.TagMapper;
import com.akbar.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    /**
     * 添加标签
     */
    @RequiresAdmin
    @Override
    public void addTag(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tagMapper.insert(tag);
    }


    /**
     * 更新标签信息
     */
    @RequiresAdmin
    @Override
    public void updateTag(Integer id, String name) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tagMapper.update(tag);
    }


    /**
     * 回显标签
     */
    @Override
    public Tag getTag(Integer id) {
        return tagMapper.getById(id);
    }

    /**
     * 删除标签
     */
    @RequiresAdmin
    @Override
    public void deleteTag(Integer id) {
        // 当前标签下有文章，不能删
        Integer count = articleMapper.countByTagId(id);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.TAG_HAS_ASSOCIATED_ARTICLES);
        }

        // 先删除 article_tag 中的关联记录
        articleTagMapper.deleteByTagId(id);
        // 再删除 tag 表中的记录
        tagMapper.delete(id);
    }


    /**
     * 获取所有标签
     */
    @Override
    public List<Tag> getTagList() {
        return tagMapper.list();
    }
}
