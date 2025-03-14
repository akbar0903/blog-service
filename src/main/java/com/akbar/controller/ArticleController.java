package com.akbar.controller;

import com.akbar.annotation.LogAnno;
import com.akbar.pojo.entity.Article;
import com.akbar.pojo.result.Result;
import com.akbar.pojo.dto.ArticleDto;
import com.akbar.pojo.vo.ArticleVO;
import com.akbar.pojo.vo.PageBean;
import com.akbar.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 添加文章
     */
    @PostMapping
    public Result<Void> addArticle(@RequestBody @Valid ArticleDto articleDto) {
        articleService.addArticle(articleDto);
        return Result.success();
    }


    /**
     * 回显文章信息
     */
    @GetMapping("/info")
    public Result<ArticleVO> getArticle(@RequestParam Integer id) {
        ArticleVO articleVO = articleService.getArticleVo(id);
        return Result.success(articleVO);
    }


    // 更新文章
    @PutMapping("/{id}")
    @LogAnno(operationType = "更新文章")
    public Result<Void> updateArticle(@PathVariable("id") Integer id,@RequestBody @Validated Article article) {
        article.setId(id);
        articleService.updateArticle(article);
        return Result.success();
    }


    // 分页获取文章列表
    @GetMapping("/list")
    public Result<PageBean> getArticleList(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "title",required = false) String title,
                                           @RequestParam(value = "categoryId",required = false) Integer categoryId,
                                           @RequestParam(value = "tagId",required = false) Integer tagId,
                                           @RequestParam(value = "state",required = false) String state) {
        return Result.success(articleService.page(pageNum, pageSize, title, categoryId, tagId, state));
    }


    // 删除文章
    @DeleteMapping("/{id}")
    @LogAnno(operationType = "删除文章")
    public Result<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return Result.success();
    }
}