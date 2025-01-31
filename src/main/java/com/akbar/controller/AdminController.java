package com.akbar.controller;

import com.akbar.service.AdminService;
import com.akbar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 管理员注册
    @PostMapping
    public Result<Void> register(@RequestParam String username, @RequestParam String password) {
        boolean result = adminService.registerAdmin(username, password);
        if (!result) {
            return Result.error("注册失败！");
        }
        return Result.success("注册成功！");
    }
}
