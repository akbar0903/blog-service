package com.akbar.controller;

import com.akbar.pojo.result.Result;
import com.akbar.service.ArticleTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article-tag")
public class ArticleTagController {

    @Autowired
    private ArticleTagService articleTagService;

    /**
     * 根据文章id获取所有标签id
     * @param articleId 文章id
     * @return 标签列表
     */
    @GetMapping
    public Result<List<Integer>> getTagIdsByArticleId(@RequestParam  Integer articleId) {
        List<Integer> tagIds = articleTagService.getTagIdsByArticleId(articleId);
        return Result.success(tagIds);
    }
}
