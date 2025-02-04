package com.akbar.controller;

import com.akbar.domain.entity.Article;
import com.akbar.service.ArticleService;
import com.akbar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 添加文章
    @PostMapping
    public Result<Void> addArticle(@RequestBody @Validated Article article) {
        articleService.addArticle(article);
        return Result.success("添加文章成功！");
    }


    // 回显文章
    @GetMapping
    public Result<Article> getArticle(@RequestParam("id") Integer id) {
        Article article = articleService.getArticle(id);
        return Result.success(article);
    }


    // 更新文章
    @PutMapping
    public Result<Void> updateArticle(@RequestBody @Validated Article article) {
        articleService.updateArticle(article);
        System.out.println(article);
        return Result.success("更新文章成功！");
    }
}