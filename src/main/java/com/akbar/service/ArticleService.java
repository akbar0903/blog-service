package com.akbar.service;

import com.akbar.pojo.entity.Article;
import com.akbar.pojo.vo.ArticleResult;
import com.akbar.pojo.vo.PageBean;

public interface ArticleService {
    void addArticle(Article article);

    ArticleResult getArticle(Integer id);

    void updateArticle(Article article);

    PageBean page(Integer pageNum, Integer pageSize, String title, Integer categoryId, Integer tagId, String state);

    void deleteArticle(Integer id);
}
