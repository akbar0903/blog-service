package com.akbar.mapper;

import com.akbar.annotation.AutoFill;
import com.akbar.enumeration.OperationType;
import com.akbar.pojo.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    @AutoFill(OperationType.INSERT)
    @Insert("insert into tag(name, admin_id, created_time, updated_time) values(#{name}, #{adminId}, #{createdTime}, #{updatedTime})")
    void insert(Tag tag);

    @AutoFill(OperationType.UPDATE)
    void update(Tag tag);

    @Select("select * from tag where id = #{id}")
    Tag getById(Integer id);

    @Delete("delete from tag where id = #{id}")
    void delete(Integer id);

    @Select("select * from tag")
    List<Tag> list();
}
