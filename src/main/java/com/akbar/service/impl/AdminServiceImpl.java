package com.akbar.service.impl;

import com.akbar.mapper.AdminMapper;
import com.akbar.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;
    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }


    @Override
    public boolean registerAdmin(String username, String password) {
        return false;
    }
}
