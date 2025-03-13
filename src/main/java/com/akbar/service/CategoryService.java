package com.akbar.service;

import com.akbar.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    // 新增分类
     void addCategory(Category category);

    // 回显分类
    Category getCategory(Integer id);

    // 修改分类
    void updateCategory(Category category);

    // 删除分类
    void deleteCategory(Integer id);

    // 获取分类列表
    List<Category> getCategoryList();

    // 搜索分类
    List<Category> searchCategory(String name);
}
