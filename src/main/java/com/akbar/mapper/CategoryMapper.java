package com.akbar.mapper;

import com.akbar.annotation.AutoFill;
import com.akbar.enumeration.OperationType;
import com.akbar.pojo.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @AutoFill(OperationType.INSERT)
    @Insert("insert into category(name, admin_id, created_time, updated_time) values(#{name}, #{adminId}, #{createdTime}, #{updatedTime})")
    void insert(Category category);

    @Select("select * from category where id = #{id}")
    Category selectById(Integer id);

    @Select("select name from category where id = #{id}")
    String selectNameById(Integer id);

    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    @Delete("delete from category where id = #{id}")
    void delete(Integer id);

    @Select("select * from category")
    List<Category> list();
}
