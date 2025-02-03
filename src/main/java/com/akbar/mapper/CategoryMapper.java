package com.akbar.mapper;

import com.akbar.domain.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    @Insert("insert into tb_category(name) values(#{name})")
    void addCategory(Category category);
}
