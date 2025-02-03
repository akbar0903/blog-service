package com.akbar.service;

import com.akbar.domain.entity.Category;

public interface CategoryService {

    // 新增分类
    boolean addCategory(Category category);

    // 回显分类
    Category getCategory(Integer id);

    // 修改分类
    boolean updateCategory(Category category);
}
