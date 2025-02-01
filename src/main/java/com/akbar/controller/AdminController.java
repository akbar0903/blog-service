package com.akbar.controller;

import com.akbar.service.AdminService;
import com.akbar.util.Result;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // 管理员注册
    @PostMapping
    public Result<Void> register(
            @RequestParam
            @NotBlank(message = "用户名不能为空")
            @Pattern(regexp = "^[a-zA-Z]{5,}$", message = "用户名必须是至少6个字母，并且只能包含英文字母")
            String username,
            @RequestParam
            @NotBlank(message = "密码不能为空")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "密码必须包含字母和数字，且至少8个字符")
            String password,
            @RequestParam
            @NotBlank(message = "确认密码不能为空")
            String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            return Result.error("密码和确认密码不匹配！");
        }

        boolean result = adminService.registerAdmin(username, password);
        if (!result) {
            return Result.error("用户名已存在！");
        }
        return Result.success("注册成功！");
    }
}
