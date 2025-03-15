package com.akbar.mapper;

import com.akbar.pojo.entity.ArticleTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleTagMapper {
    @Delete("delete from article_tag where tag_id = #{tagId}")
    void deleteByTagId(Integer tagId);

    @Insert("insert into article_tag(article_id, tag_id) VALUES (#{articleId}, #{tagId})")
    void insert(ArticleTag articleTag);

    @Delete("delete from article_tag where article_id = #{articleId}")
    void deleteByArticleId(Integer articleId);
}
