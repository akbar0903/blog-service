package com.akbar.controller;

import com.akbar.service.AdminService;
import com.akbar.util.Result;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            @Pattern(regexp = "^[a-zA-Z]{5,}$", message = "用户名必须是至少5个字母，并且只能包含英文字母")
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


    // 管理员登录
    @PostMapping("/login")
    public Result<Void> login(
            @RequestParam
            @NotBlank(message = "用户名不能为空")
            String username,
            @RequestParam
            @NotBlank(message = "密码不能为空")
            String password) {

        boolean result = adminService.loginAdmin(username, password);
        if (!result) {
            return Result.error("用户名或密码错误！");
        }
        return Result.success("登录成功！");
    }


    // 修改管理员密码
    @PatchMapping
    public Result<Void> modifyPassword(
            @RequestParam
            @NotBlank(message = "用户名不能为空！")
            String username,
            @RequestParam
            @NotBlank(message = "旧密码不能为空！")
            String oldPassword,
            @RequestParam
            @NotBlank(message = "新密码不能为空！")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "密码必须包含字母和数字，且至少8个字符！")
            String newPassword,
            @RequestParam
            @NotBlank(message = "确认密码不能为空！")
            String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return Result.error("新密码和确认密码不匹配！");
        }

        boolean result = adminService.modifyPassword(username ,oldPassword, newPassword);
        if (!result) {
            return Result.error("旧密码错误！");
        }
        return Result.success("密码修改成功！");
    }
}
