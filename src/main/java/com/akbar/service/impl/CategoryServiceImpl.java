package com.akbar.service.impl;

import com.akbar.domain.entity.Category;
import com.akbar.mapper.CategoryMapper;
import com.akbar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    // 新增分类
    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }


    // 回显分类
    @Override
    public Category getCategory(Integer id) {
        return categoryMapper.getCategory(id);
    }


    // 修改分类
    @Override
    public void updateCategory(Category category) {
        LocalDateTime now = LocalDateTime.now();
        category.setUpdatedTime(now);
        categoryMapper.updateCategory(category);
    }


    // 删除分类
    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }


    // 查询所有分类
    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }


    // 搜索分类
    @Override
    public List<Category> searchCategory(String name) {
        return categoryMapper.searchCategory(name);
    }
}
