package com.akbar.service.impl;

import com.akbar.domain.entity.Category;
import com.akbar.mapper.CategoryMapper;
import com.akbar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    // 新增分类
    @Override
    public boolean addCategory(Category category) {
        categoryMapper.addCategory(category);
        return true;
    }


    // 回显分类
    @Override
    public Category getCategory(Integer id) {
        return categoryMapper.getCategory(id);
    }


    // 修改分类
    @Override
    public boolean updateCategory(Category category) {
        LocalDateTime now = LocalDateTime.now();
        category.setUpdatedTime(now);
        categoryMapper.updateCategory(category);
        return true;
    }
}
