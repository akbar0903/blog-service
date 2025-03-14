package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
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
