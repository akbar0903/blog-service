package com.akbar.service.impl;
import com.akbar.domain.entity.Admin;
import com.akbar.mapper.AdminMapper;
import com.akbar.service.AdminService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    // 管理员注册
    @Override
    public boolean registerAdmin(String username, String password) {
        Admin byUsername = adminMapper.findByUsername(username);
        if (byUsername != null) {
            return false;
        }

        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(hashed);
        adminMapper.register(admin);
        return true;
    }


    // 管理员登录
    @Override
    public boolean loginAdmin(String username, String password) {
        Admin byUsername = adminMapper.findByUsername(username);
        if (byUsername == null) {
            return false;
        }
        return BCrypt.checkpw(password, byUsername.getPassword());
    }


    // 修改密码
    @Override
    public boolean modifyPassword(String username, String oldPassword, String newPassword) {
        Admin byUsername = adminMapper.findByUsername(username);
        if (byUsername == null) {
            return false;
        }
        if (!BCrypt.checkpw(oldPassword, byUsername.getPassword())) {
            return false;
        }
        String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        LocalDateTime now = LocalDateTime.now();
        byUsername.setUpdatedTime(now);

        byUsername.setPassword(hashed);
        adminMapper.modifyPassword(byUsername);
        return true;
    }


    // 更新管理员信息
    @Override
    public boolean updateAdminInfo(String username, String nickname, String email, String githubUrl, String bilibiliUrl, String giteeUrl) {
        Admin byUsername = adminMapper.findByUsername(username);
        if (byUsername == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();

        byUsername.setNickname(nickname);
        byUsername.setEmail(email);
        byUsername.setGithubUrl(githubUrl);
        byUsername.setBilibiliUrl(bilibiliUrl);
        byUsername.setGiteeUrl(giteeUrl);
        byUsername.setUpdatedTime(now);
        adminMapper.updateAdminInfo(byUsername);
        return true;
    }


    // 获取管理员信息
    @Override
    public Admin getAdminInfo(String username) {
        return adminMapper.findByUsername(username);
    }


    // 上传头像
    @Override
    public boolean uploadAvatar(String username, String avatar) {
        Admin byUsername = adminMapper.findByUsername(username);
        if (byUsername == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        byUsername.setAvatar(avatar);
        byUsername.setUpdatedTime(now);
        adminMapper.uploadAvatar(byUsername);
        return true;
    }
}
