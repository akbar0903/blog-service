package com.akbar.service.impl;
import com.akbar.domain.entity.Admin;
import com.akbar.mapper.AdminMapper;
import com.akbar.service.AdminService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
