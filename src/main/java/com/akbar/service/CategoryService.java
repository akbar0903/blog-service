package com.akbar.service;

import com.akbar.domain.entity.Category;

import java.util.List;

public interface CategoryService {

    // 新增分类
    boolean addCategory(Category category);

    // 回显分类
    Category getCategory(Integer id);

    // 修改分类
    boolean updateCategory(Category category);

    // 删除分类
    boolean deleteCategory(Integer id);

    // 获取分类列表
    List<Category> getCategoryList();

    // 搜索分类
    List<Category> searchCategory(String name);
}
