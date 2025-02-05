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
    @GetMapping("/{id}")
    public Result<ArticleResult> getArticle(@PathVariable("id") Integer id) {
        ArticleResult articleResult = articleService.getArticle(id);
        return Result.success(articleResult);
    }


    // 更新文章
    @PutMapping("/{id}")
    public Result<Void> updateArticle(@PathVariable("id") Integer id,@RequestBody @Validated Article article) {
        article.setId(id);
        articleService.updateArticle(article);
        return Result.success("更新文章成功！");
    }


    // 分页获取文章列表
    @GetMapping("/articles")
    public Result<PageBean> getArticleList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "title",required = false) String title,
                                           @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                           @RequestParam(value = "tagId",required = false) Integer tagId,
                                           @RequestParam(value = "state",required = false) String state) {
        return Result.success(articleService.page(page, pageSize, title, categoryId, tagId, state));
    }


    // 删除文章
    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return Result.success("删除文章成功！");
    }
}