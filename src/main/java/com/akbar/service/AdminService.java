package com.akbar.service;

import com.akbar.pojo.dto.AdminLoginDto;
import com.akbar.pojo.dto.AdminUpdateDto;
import com.akbar.pojo.dto.PasswordEditDto;
import com.akbar.pojo.entity.Admin;
import com.akbar.pojo.vo.admin.AdminVo;
import jakarta.validation.Valid;

public interface AdminService {

    Admin loginAdmin(@Valid AdminLoginDto adminLoginDto);

    void updatePassword(PasswordEditDto passwordEditDto);

    void updateInfo(AdminUpdateDto adminUpdateDto);

    void uploadAvatar(Integer id, String avatar);

    AdminVo getInfo(Integer id);
}
