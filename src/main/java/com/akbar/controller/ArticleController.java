package com.akbar.controller;

import com.akbar.domain.entity.Article;
import com.akbar.domain.vo.ArticleResult;
import com.akbar.domain.vo.PageBean;
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
    public Result<ArticleResult> getArticle(@RequestParam("id") Integer id) {
        ArticleResult articleResult = articleService.getArticle(id);
        return Result.success(articleResult);
    }


    // 更新文章
    @PutMapping
    public Result<Void> updateArticle(@RequestBody @Validated Article article) {
        articleService.updateArticle(article);
        System.out.println(article);
        return Result.success("更新文章成功！");
    }


    // 分页获取文章列表
    @GetMapping("/list")
    public Result<PageBean> getArticleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "title",required = false) String title,
                                           @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                           @RequestParam(value = "tagId",required = false) Integer tagId,
                                           @RequestParam(value = "state",required = false) String state) {
        return Result.success(articleService.page(page, pageSize, title, categoryId, tagId, state));
    }
}