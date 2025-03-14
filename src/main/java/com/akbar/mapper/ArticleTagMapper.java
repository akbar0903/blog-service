package com.akbar.mapper;

import com.akbar.pojo.entity.ArticleTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleTagMapper {
    @Delete("delete from article_tag where tag_id = #{tagId}")
    void deleteByTagId(Integer tagId);

    @Insert("insert into article_tag(article_id, tag_id) VALUES (#{articleId}, #{tagId})")
    void insert(ArticleTag articleTag);

    // 根据 article_id 查询关联的标签 ID 列表
    @Select("select tag_id from article_tag where article_id = #{articleId}")
    List<Integer> selectTagIdsByArticleId(Integer articleId);
}
