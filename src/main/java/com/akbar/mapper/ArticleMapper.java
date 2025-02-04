package com.akbar.mapper;

import com.akbar.domain.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    void addArticle(Article article);

    Article getArticle(Integer id);
}
