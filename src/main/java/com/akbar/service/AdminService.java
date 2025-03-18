package com.akbar.service;

import com.akbar.pojo.dto.admin.AdminLoginDto;
import com.akbar.pojo.dto.admin.AdminUpdateDto;
import com.akbar.pojo.dto.admin.PasswordEditDto;
import com.akbar.pojo.entity.Admin;
import com.akbar.pojo.vo.AdminVo;

public interface AdminService {

    Admin loginAdmin(AdminLoginDto adminLoginDto);

    void updatePassword(PasswordEditDto passwordEditDto);

    void updateInfo(AdminUpdateDto adminUpdateDto);

    void uploadAvatar(Integer id, String avatar);

    AdminVo getCurrentAdminInfo();

    AdminVo getInfo();
}
