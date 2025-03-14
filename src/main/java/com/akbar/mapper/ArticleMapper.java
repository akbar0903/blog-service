package com.akbar.mapper;

import com.akbar.annotation.AutoFill;
import com.akbar.enumeration.OperationType;
import com.akbar.pojo.entity.Article;
import com.akbar.pojo.dto.ArticleDto;
import com.akbar.pojo.vo.ArticleVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @AutoFill(OperationType.INSERT)
    void insert(Article article);

    // 查询文章的基本信息
    @Select("select * from article where id = #{id}")
    Article selectById(Integer id);

    // 往ArticleVO封装文章基本信息
    ArticleVO selectArticleVoById(Integer id);

    void updateArticle(Article article);

    List<ArticleDto> pageArticle(String title, Integer categoryId, Integer tagId, String state);

    @Delete(("delete from tb_article where id = #{id}"))
    void deleteArticle(Integer id);

    @Select("select count(id) from article where tag_id = #{tagId}")
    Integer countByTagId(Integer tagId);

    @Select("select count(id) from article where category_id = #{categoryId}")
    Integer countByCategoryId(Integer categoryId);
}
