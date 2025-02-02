package com.akbar.service;

import com.akbar.domain.entity.Admin;
import jakarta.validation.constraints.NotBlank;

public interface AdminService {

    // 管理员注册
    boolean registerAdmin(String username, String confirmPassword);

    // 管理员登录
    boolean loginAdmin( String username, String password);
}
