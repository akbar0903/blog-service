package com.akbar.service;

import com.akbar.pojo.entity.Category;

import java.util.List;

public interface CategoryService {

    void addCategory(String name);

    Category getCategory(Integer id);

    void updateCategory(Integer id, String name);

    void deleteCategory(Integer id);

    List<Category> getCategoryList();
}
