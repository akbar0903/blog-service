package com.akbar.controller;

import com.akbar.constant.JwtClaimsConstant;
import com.akbar.constant.MessageConstant;
import com.akbar.pojo.dto.admin.AdminLoginDto;
import com.akbar.pojo.dto.admin.AdminUpdateDto;
import com.akbar.pojo.dto.admin.PasswordEditDto;
import com.akbar.pojo.entity.Admin;
import com.akbar.pojo.result.Result;
import com.akbar.pojo.vo.admin.AdminVo;
import com.akbar.properties.JwtProperties;
import com.akbar.service.AdminService;
import com.akbar.util.JwtUtil;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid AdminLoginDto adminLoginDto) {
        Admin admin = adminService.loginAdmin(adminLoginDto);

        // 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, admin.getId());
        String token = JwtUtil.generateJwt(
                jwtProperties.getSecretKey(),
                jwtProperties.getTTl(),
                claims
        );

        return Result.success(token);
    }


    /**
     * 修改管理员密码
     */
    @PatchMapping
    public Result<String> modifyPassword(@RequestBody @Valid PasswordEditDto passwordEditDto) {
        adminService.updatePassword(passwordEditDto);
        return Result.success();
    }


    /**
     * 更新管理员信息
     */
    @PutMapping
    public Result<AdminVo> modifyInfo(@RequestBody @Valid AdminUpdateDto adminUpdateDto) {
        adminService.updateInfo(adminUpdateDto);
        return Result.success();
    }


    /**
     * 上传管理员头像
     */
    @PatchMapping("/upload-avatar")
    public Result<String> uploadAvatar(
            @RequestParam
            Integer id,

            @RequestParam
            @URL(message = MessageConstant.INVALID_URL)
            String avatar) {

        adminService.uploadAvatar(id, avatar);
        return Result.success();
    }


    /**
     * 获取管理员信息
     */
    @GetMapping("/info")
    public Result<AdminVo> getInfo(@RequestParam Integer id) {
        AdminVo adminVo = adminService.getInfo(id);
        return Result.success(adminVo);
    }
}
