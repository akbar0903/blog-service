package com.akbar.service.impl;

import com.akbar.domain.entity.Article;
import com.akbar.domain.vo.ArticleResult;
import com.akbar.domain.vo.PageBean;
import com.akbar.mapper.ArticleMapper;
import com.akbar.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public ArticleResult getArticle(Integer id) {
        return articleMapper.getArticle(id);
    }

    @Override
    public void updateArticle(Article article) {
        LocalDateTime now = LocalDateTime.now();
        article.setUpdatedTime(now);
        articleMapper.updateArticle(article);
    }

    @Override
    public PageBean page(Integer pageNum, Integer pageSize, String title, Integer categoryId, Integer tagId, String state) {
        PageHelper.startPage(pageNum, pageSize);

        List<ArticleResult> articleResults = articleMapper.pageArticle(title, categoryId, tagId, state);
        Page<ArticleResult> page = (Page<ArticleResult>) articleResults;
        return new PageBean(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }
}
