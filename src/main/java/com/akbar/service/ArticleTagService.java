package com.akbar.service;

import java.util.List;

public interface ArticleTagService {
    List<Integer> getTagIdsByArticleId(Integer articleId);
}
