package com.akbar.service.impl;

import com.akbar.annotation.RequiresAdmin;
import com.akbar.constant.MessageConstant;
import com.akbar.exception.DeletionNotAllowedException;
import com.akbar.mapper.ArticleMapper;
import com.akbar.pojo.entity.Category;
import com.akbar.mapper.CategoryMapper;
import com.akbar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 新增分类
     */
    @RequiresAdmin
    @Override
    public void addCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryMapper.insert(category);
    }


    /**
     * 回显分类
     */
    @Override
    public Category getCategory(Integer id) {
        return categoryMapper.selectById(id);
    }


    /**
     * 更新分类
     */
    @RequiresAdmin
    @Override
    public void updateCategory(Integer id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        categoryMapper.update(category);
    }


    /**
     * 删除分类
     */
    @RequiresAdmin
    @Override
    public void deleteCategory(Integer id) {
        // 当前分类下有文章，不能删除
        Integer count = articleMapper.countByCategoryId(id);
        if (count > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_HAS_ASSOCIATED_ARTICLES);
        }

        categoryMapper.delete(id);
    }


    /**
     * 获取所有分类
     */
    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.list();
    }
}
