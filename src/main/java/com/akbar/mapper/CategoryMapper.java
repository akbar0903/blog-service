package com.akbar.mapper;

import com.akbar.domain.entity.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CategoryMapper {

    @Insert("insert into tb_category(name) values(#{name})")
    void addCategory(Category category);

    @Select("select * from tb_category where id = #{id}")
    Category getCategory(Integer id);

    @Update("update tb_category set name = #{name}, updated_time= #{updatedTime} where id = #{id}")
    void updateCategory(Category category);
}
