package com.akbar.controller;

import com.akbar.domain.entity.Category;
import com.akbar.service.CategoryService;
import com.akbar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 新增分类
    @PostMapping
    public Result<Void> addCategory(@RequestBody @Validated Category category) {
        boolean result = categoryService.addCategory(category);
        if (!result) {
            return Result.error("新增分类失败！");
        }
        return Result.success("新增分类成功！");
    }
}
