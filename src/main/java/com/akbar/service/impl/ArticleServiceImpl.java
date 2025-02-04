package com.akbar.service.impl;

import com.akbar.domain.entity.Article;
import com.akbar.mapper.ArticleMapper;
import com.akbar.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articleMapper;
    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void addArticle(Article article) {
        articleMapper.addArticle(article);
    }

    @Override
    public Article getArticle(Integer id) {
        return articleMapper.getArticle(id);
    }

    @Override
    public void updateArticle(Article article) {
        LocalDateTime now = LocalDateTime.now();
        article.setUpdatedTime(now);
        articleMapper.updateArticle(article);
    }
}
