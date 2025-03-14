package com.akbar.mapper;

import com.akbar.pojo.entity.Article;
import com.akbar.pojo.vo.ArticleResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    void addArticle(Article article);

    ArticleResult getById(Integer id);

    void updateArticle(Article article);

    List<ArticleResult> pageArticle(String title, Integer categoryId, Integer tagId, String state);

    @Delete(("delete from tb_article where id = #{id}"))
    void deleteArticle(Integer id);

    @Select("select count(id) from article where tag_id = #{tagId}")
    Integer countByTagId(Integer tagId);
}
