package com.akbar.service;

import com.akbar.pojo.dto.article.ArticlePageDto;
import com.akbar.pojo.dto.article.ArticleUpdateDto;
import com.akbar.pojo.dto.article.ArticleDto;
import com.akbar.pojo.result.PageResult;
import com.akbar.pojo.vo.ArticleVo;

public interface ArticleService {
    void addArticle(ArticleDto articleDto);

    ArticleVo getArticleVo(Integer id);

    void updateArticle(ArticleUpdateDto articleUpdateDto);

    void deleteArticle(Integer id);

    PageResult getArticleList(ArticlePageDto articlePageDto);
}
