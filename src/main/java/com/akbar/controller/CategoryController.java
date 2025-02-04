package com.akbar.controller;

import com.akbar.domain.entity.Category;
import com.akbar.service.CategoryService;
import com.akbar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    // 回显分类
    @GetMapping
    public Result<Category> getCategory(@RequestParam(value = "id") Integer id) {
        Category category = categoryService.getCategory(id);
        return Result.success(category);
    }


    // 修改分类
    @PutMapping
    public Result<Void> updateCategory(@RequestBody @Validated Category category) {
        boolean result = categoryService.updateCategory(category);
        if (!result) {
            return Result.error("修改分类失败！");
        }
        return Result.success("修改分类成功！");
    }


    // 删除分类
    @DeleteMapping
    public Result<Void> deleteCategory(@RequestParam(value = "id") Integer id) {
        boolean result = categoryService.deleteCategory(id);
        if (!result) {
            return Result.error("删除分类失败！");
        }
        return Result.success("删除分类成功！");
    }


    // 获取分类列表
    @GetMapping("/list")
    public Result<List<Category>> getCategoryList() {
        List<Category> categoryList = categoryService.getCategoryList();
        return Result.success(categoryList);
    }


    // 分类搜索
    @GetMapping("/search")
    public Result<List<Category>> searchCategory(@RequestParam(value = "name") String name) {
        List<Category> categoryList = categoryService.searchCategory(name);
        return Result.success(categoryList);
    }
}
