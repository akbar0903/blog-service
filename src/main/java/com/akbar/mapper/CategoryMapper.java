package com.akbar.mapper;

import com.akbar.domain.entity.Category;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CategoryMapper {

    @Insert("insert into tb_category(name) values(#{name})")
    void addCategory(Category category);

    @Select("select * from tb_category where id = #{id}")
    Category getCategory(Integer id);

    @Update("update tb_category set name = #{name}, updated_time= #{updatedTime} where id = #{id}")
    void updateCategory(Category category);

    @Delete("delete from tb_category where id = #{id}")
    void deleteCategory(Integer id);
}
