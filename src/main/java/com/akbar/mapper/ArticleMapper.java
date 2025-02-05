package com.akbar.mapper;

import com.akbar.domain.entity.Article;
import com.akbar.domain.vo.ArticleResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {

    void addArticle(Article article);

    ArticleResult getArticle(Integer id);

    void updateArticle(Article article);

    List<ArticleResult> pageArticle(String title, Integer categoryId, Integer tagId, String state);

    @Delete(("delete from tb_article where id = #{id}"))
    void deleteArticle(Integer id);
}
