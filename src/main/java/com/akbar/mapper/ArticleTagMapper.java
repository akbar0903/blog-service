package com.akbar.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleTagMapper {
    @Delete("delete from article_tag where tag_id = #{tagId}")
    void deleteByTagId(Integer tagId);
}
