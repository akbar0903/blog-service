package com.akbar.mapper;

import com.akbar.domain.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {

    // 根据用户名查询用户
    @Select("select * from tb_admin where username=#{username}")
    Admin findByUsername(String username);

    // 管理员注册
    void register(Admin admin);

    // 修改密码
    @Update("update tb_admin set password=#{password}, updated_time=#{updatedTime} where username=#{username}")
    void modifyPassword(Admin admin);

    // 更新管理员信息
    void updateAdminInfo(Admin admin);

    // 上传头像
    void uploadAvatar(Admin byUsername);
}
