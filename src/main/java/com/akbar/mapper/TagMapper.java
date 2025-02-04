package com.akbar.mapper;

import com.akbar.domain.entity.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TagMapper {

    @Insert("insert into tb_tag(name) values(#{name})")
    void addTag(Tag tag);

    @Update("update tb_tag set name = #{name} where id = #{id}")
    void updateTag(Tag tag);

    @Select("select * from tb_tag where id = #{id}")
    Tag getTag(Integer id);

    @Delete("delete from tb_tag where id = #{id}")
    void deleteTag(Integer id);

    @Select("select * from tb_tag")
    List<Tag> getTagList();

    @Select("select * from tb_tag where name like concat('%', #{name}, '%')")
    List<Tag> searchTag(String name);
}
