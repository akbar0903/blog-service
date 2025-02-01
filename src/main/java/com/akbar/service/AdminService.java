package com.akbar.service;

import com.akbar.domain.entity.Admin;

public interface AdminService {

    // 管理员注册
    boolean registerAdmin(String username, String confirmPassword);
}
