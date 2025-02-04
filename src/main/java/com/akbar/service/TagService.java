package com.akbar.service;

import com.akbar.domain.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(Tag tag);

    void updateTag(Tag tag);

    Tag getTag(Integer id);

    void deleteTag(Integer id);

    List<Tag> getTagList();

    List<Tag> searchTag(String name);
}
