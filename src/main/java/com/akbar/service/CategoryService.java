package com.akbar.service;

import com.akbar.domain.entity.Category;

public interface CategoryService {

    // 新增分类
    boolean addCategory(Category category);

    Category getCategory(Integer id);
}
