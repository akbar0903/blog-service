package com.akbar.service;

import com.akbar.domain.entity.Article;
import com.akbar.domain.vo.ArticleResult;
import com.akbar.domain.vo.PageBean;

public interface ArticleService {
    void addArticle(Article article);

    ArticleResult getArticle(Integer id);

    void updateArticle(Article article);

    PageBean page(Integer pageNum, Integer pageSize, String title, Integer categoryId, Integer tagId, String state);
}
