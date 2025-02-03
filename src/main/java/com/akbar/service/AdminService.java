package com.akbar.service;

public interface AdminService {

    // 管理员注册
    boolean registerAdmin(String username, String confirmPassword);

    // 管理员登录
    boolean loginAdmin(String username, String password);

    // 修改密码
    boolean modifyPassword(String username, String oldPassword, String newPassword);

    // 更新管理员信息
    boolean updateAdminInfo(String username, String nickname, String email, String githubUrl, String bilibiliUrl, String giteeUrl);
}
