package com.akbar.service;

import com.akbar.domain.entity.Article;

public interface ArticleService {
    void addArticle(Article article);

    Article getArticle(Integer id);

    void updateArticle(Article article);
}
