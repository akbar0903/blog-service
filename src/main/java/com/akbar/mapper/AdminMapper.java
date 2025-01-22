package com.akbar.mapper;

import com.akbar.domain.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    // 根据用户名查询用户
    @Select("select * from tb_admin where username=#{username}")
    Admin findByUsername(String username);

    // 管理员注册
    void register(String username, String password);
}
