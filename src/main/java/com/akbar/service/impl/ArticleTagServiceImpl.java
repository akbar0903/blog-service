package com.akbar.service.impl;

import com.akbar.mapper.ArticleTagMapper;
import com.akbar.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {
    @Autowired
    ArticleTagMapper articleTagMapper;

    @Override
    public List<Integer> getTagIdsByArticleId(Integer articleId) {
        return articleTagMapper.getByArticleId(articleId);
    }
}
