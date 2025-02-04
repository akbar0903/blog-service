package com.akbar.service.impl;

import com.akbar.domain.entity.Tag;
import com.akbar.mapper.TagMapper;
import com.akbar.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    @Autowired
    public TagServiceImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void addTag(Tag tag) {
        tagMapper.addTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        tagMapper.updateTag(tag);
    }

    @Override
    public Tag getTag(Integer id) {
        return tagMapper.getTag(id);
    }

    @Override
    public void deleteTag(Integer id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTagList() {
        return tagMapper.getTagList();
    }

    @Override
    public List<Tag> searchTag(String name) {
        return tagMapper.searchTag(name);
    }
}
