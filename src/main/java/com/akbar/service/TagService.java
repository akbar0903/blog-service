package com.akbar.service;

import com.akbar.pojo.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(String name);

    void updateTag(Integer id, String name);

    Tag getTag(Integer id);

    void deleteTag(Integer id);

    List<Tag> getTagList();
}
