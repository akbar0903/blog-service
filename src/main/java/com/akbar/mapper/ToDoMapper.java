package com.akbar.mapper;

import com.akbar.annotation.AutoFill;
import com.akbar.enumeration.OperationType;
import com.akbar.pojo.entity.ToDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ToDoMapper {

    @AutoFill(OperationType.INSERT)
    @Insert("insert into feature_plan(title, description, admin_id, created_time, updated_time) " +
            "values(#{title}, #{description},#{adminId}, #{createdTime}, #{updatedTime})")
    void insert(ToDo plan);

    @Select("select * from feature_plan where id = #{id}")
    ToDo selectById(Integer id);

    @AutoFill(OperationType.UPDATE)
    void update(ToDo plan);

    @Delete("delete from feature_plan where id = #{id}")
    void delete(Integer id);

    @Select("select * from feature_plan")
    List<ToDo> list();
}
