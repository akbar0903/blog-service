package com.akbar.mapper;

import com.akbar.pojo.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where username=#{username}")
    Admin getByUserName(String username);

    @Select("select * from admin where id = #{id}")
    Admin getById(Integer id);

    void update(Admin admin);
}
