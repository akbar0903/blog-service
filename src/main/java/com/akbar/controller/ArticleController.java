package com.akbar.controller;

import com.akbar.pojo.dto.article.ArticlePageDto;
import com.akbar.pojo.dto.article.ArticleUpdateDto;
import com.akbar.pojo.result.PageResult;
import com.akbar.pojo.result.Result;
import com.akbar.pojo.dto.article.ArticleDto;
import com.akbar.pojo.vo.ArticleVO;
import com.akbar.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * 更新文章
     */
    @PutMapping
    public Result<Void> updateArticle(@RequestBody @Valid ArticleUpdateDto articleUpdateDto) {
        articleService.updateArticle(articleUpdateDto);
        return Result.success();
    }


    /**
     * 删除文章
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return Result.success();
    }


    /**
     * 分页获取文章列表
     */
    @GetMapping("/list")
    public Result<PageResult> list(@RequestBody ArticlePageDto articlePageDto) {
        PageResult pageResult = articleService.getArticleList(articlePageDto);
        return Result.success(pageResult);
    }
}