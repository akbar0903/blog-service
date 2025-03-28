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
    @Insert("insert into to_do(title, type, admin_id, created_time, updated_time) " +
            "values(#{title}, #{type},#{adminId}, #{createdTime}, #{updatedTime})")
    void insert(ToDo toDo);

    @Select("select * from to_do where id = #{id}")
    ToDo selectById(Integer id);

    @AutoFill(OperationType.UPDATE)
    void update(ToDo toDo);

    @Delete("delete from to_do where id = #{id}")
    void delete(Integer id);

    @Select("select * from to_do")
    List<ToDo> list();
}
