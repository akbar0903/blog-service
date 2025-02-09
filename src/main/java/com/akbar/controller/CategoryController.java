package com.akbar.controller;

import com.akbar.annotation.LogAnno;
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
    @LogAnno(operationType = "新增分类")
    public Result<Void> addCategory(@RequestBody @Validated Category category) {
        categoryService.addCategory(category);
        return Result.success("新增分类成功！");
    }


    // 回显分类
    @GetMapping("/{id}")
    public Result<Category> getCategory(@PathVariable Integer id) {
        Category category = categoryService.getCategory(id);
        return Result.success(category);
    }


    // 修改分类
    @PutMapping("/{id}")
    @LogAnno(operationType = "修改分类")
    public Result<Void> updateCategory(@PathVariable Integer id, @RequestBody @Validated Category category) {
        category.setId(id);
        categoryService.updateCategory(category);
        return Result.success("修改分类成功！");
    }


    // 删除分类
    @DeleteMapping("/{id}")
    @LogAnno(operationType = "删除分类")
    public Result<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return Result.success("删除分类成功！");
    }


    // 获取分类列表
    @GetMapping
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
