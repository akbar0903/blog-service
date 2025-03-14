package com.akbar.service;

import com.akbar.pojo.entity.Article;
import com.akbar.pojo.dto.ArticleDto;
import com.akbar.pojo.vo.ArticleVO;
import com.akbar.pojo.vo.PageBean;

public interface ArticleService {
    void addArticle(ArticleDto articleDto);

    ArticleVO getArticleVo(Integer id);

    void updateArticle(Article article);

    PageBean page(Integer pageNum, Integer pageSize, String title, Integer categoryId, Integer tagId, String state);

    void deleteArticle(Integer id);
}
