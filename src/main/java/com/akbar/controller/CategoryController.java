package com.akbar.controller;

import com.akbar.constant.MessageConstant;
import com.akbar.pojo.entity.Category;
import com.akbar.pojo.result.Result;
import com.akbar.service.CategoryService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 新增分类
     */
    @PostMapping
    public Result<Void> addCategory(
            @RequestParam
            @NotBlank(message = MessageConstant.CATEGORY_NAME_CANT_BE_EMPTY)
            @Size(max = MessageConstant.CATEGORY_NAME_MAX_LENGTH, message = MessageConstant.CATEGORY_NAME_TOO_LONG)
            String name) {
        categoryService.addCategory(name);
        return Result.success();
    }


    /**
     * 回显分类
     */
    @GetMapping("/info")
    public Result<Category> getCategory(@RequestParam Integer id) {
        Category category = categoryService.getCategory(id);
        return Result.success(category);
    }


    /**
     * 更新分类
     */
    @PutMapping
    public Result<Void> updateCategory(
            @RequestParam Integer id,

            @RequestParam
            @NotBlank(message = MessageConstant.CATEGORY_NAME_CANT_BE_EMPTY)
            @Size(max = MessageConstant.CATEGORY_NAME_MAX_LENGTH, message = MessageConstant.CATEGORY_NAME_TOO_LONG)
            String name) {

        categoryService.updateCategory(id, name);
        return Result.success();
    }


    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }


    /**
     * 获取所有分类
     */
    @GetMapping("/list")
    public Result<List<Category>> getCategoryList() {
        List<Category> categoryList = categoryService.getCategoryList();
        return Result.success(categoryList);
    }
}
