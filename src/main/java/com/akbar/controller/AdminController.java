package com.akbar.controller;

import com.akbar.domain.entity.Admin;
import com.akbar.service.AdminService;
import com.akbar.util.Result;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
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
            @RequestParam(value = "username")
            @NotBlank(message = "用户名不能为空")
            @Pattern(regexp = "^[a-zA-Z]{5,}$", message = "用户名必须是至少5个字母，并且只能包含英文字母")
            String username,

            @RequestParam(value = "password")
            @NotBlank(message = "密码不能为空")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "密码必须包含字母和数字，且至少8个字符")
            String password,

            @RequestParam(value = "confirmPassword")
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
            @RequestParam(value = "username")
            @NotBlank(message = "用户名不能为空")
            String username,

            @RequestParam(value = "password")
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
            @RequestParam(value = "username")
            @NotBlank(message = "用户名不能为空！")
            String username,

            @RequestParam(value = "oldPassword")
            @NotBlank(message = "旧密码不能为空！")
            String oldPassword,

            @RequestParam(value = "newPassword")
            @NotBlank(message = "新密码不能为空！")
            @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "密码必须包含字母和数字，且至少8个字符！")
            String newPassword,

            @RequestParam(value = "confirmPassword")
            @NotBlank(message = "确认密码不能为空！")
            String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            return Result.error("新密码和确认密码不匹配！");
        }

        boolean result = adminService.modifyPassword(username, oldPassword, newPassword);
        if (!result) {
            return Result.error("旧密码错误！");
        }
        return Result.success("密码修改成功！");
    }


    // 更新和完善管理员信息
    @PatchMapping("/update-info")
    public Result<Void> updateAdminInfo(
            @RequestParam(value = "username")
            @NotBlank(message = "用户名不能为空！")
            String username,

            @RequestParam(value = "nickname", required = false)
            String nickname,

            @RequestParam(value = "email", required = false)
            @Email(message = "邮箱格式不正确！")
            String email,

            @RequestParam(value = "githubUrl", required = false)
            @URL(message = "GitHub地址格式不正确！")
            String githubUrl,

            @RequestParam(value = "bilibiliUrl", required = false)
            @URL(message = "Bilibili地址格式不正确！")
            String bilibiliUrl,

            @RequestParam(value = "giteeUrl", required = false)
            @URL(message = "Gitee地址格式不正确！")
            String giteeUrl) {

        boolean result = adminService.updateAdminInfo(username, nickname, email, githubUrl, bilibiliUrl, giteeUrl);
        if (!result) {
            return Result.error("更新失败！");
        }
        return Result.success("更新成功！");
    }


    // 获取管理员信息
    @GetMapping
    public Result<Admin> getAdminInfo(@RequestParam(value = "username") String username) {
        Admin admin = adminService.getAdminInfo(username);
        return Result.success(admin);
    }
}
