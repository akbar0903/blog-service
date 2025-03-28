package com.akbar.service.impl;
import com.akbar.annotation.RequiresAdmin;
import com.akbar.constant.MessageConstant;
import com.akbar.constant.RoleConstant;
import com.akbar.context.BaseContext;
import com.akbar.exception.AccountNotFoundException;
import com.akbar.exception.PasswordErrorException;
import com.akbar.pojo.dto.admin.AdminLoginDto;
import com.akbar.pojo.dto.admin.AdminUpdateDto;
import com.akbar.pojo.dto.admin.PasswordEditDto;
import com.akbar.pojo.entity.Admin;
import com.akbar.mapper.AdminMapper;
import com.akbar.pojo.vo.AdminVo;
import com.akbar.service.AdminService;
import com.akbar.util.BCryptUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    /**
     * 管理员登录
     */
    @Override
    public Admin loginAdmin(@Valid AdminLoginDto adminLoginDto) {
        String username = adminLoginDto.getUsername();
        String password = adminLoginDto.getPassword();

        Admin admin = adminMapper.getByUserName(username);

        if (admin == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if (!BCryptUtil.checkPassword(password, admin.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return admin;
    }


    /**
     * 修改管理员密码
     */
    @RequiresAdmin
    @Override
    public void updatePassword(PasswordEditDto passwordEditDto) {
        Integer id = passwordEditDto.getId();
        String oldPassword = passwordEditDto.getOldPassword();
        String newPassword = passwordEditDto.getNewPassword();
        String confirmPassword = passwordEditDto.getConfirmPassword();

        Admin admin = adminMapper.getById(id);
        if (admin == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if (!BCryptUtil.checkPassword(oldPassword, admin.getPassword())) {
            throw new PasswordErrorException(MessageConstant.OLD_PASSWORD_ERROR);
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new PasswordErrorException(MessageConstant.PASSWORDS_DIFFERENT_ERROR);
        }

        admin.setPassword(BCryptUtil.generateHashPwd(newPassword));
        admin.setUpdatedTime(LocalDateTime.now());

        adminMapper.update(admin);
    }


    /**
     * 更新管理员信息
     */
    @RequiresAdmin
    @Override
    public void updateInfo(AdminUpdateDto adminUpdateDto) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminUpdateDto, admin);
        admin.setUpdatedTime(LocalDateTime.now());
        adminMapper.update(admin);
    }


    /**
     * 上传头像
     */
    @RequiresAdmin
    @Override
    public void uploadAvatar(Integer id, String avatar) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setAvatar(avatar);
        admin.setUpdatedTime(LocalDateTime.now());

        adminMapper.update(admin);
    }


    /**
     * 根据当前登录用户id获取管理员信息
     */
    @Override
    public AdminVo getCurrentAdminInfo() {
        Integer adminId = BaseContext.getCurrentAdminId();
        Admin admin = adminMapper.getById(adminId);
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(admin, adminVo);
        return adminVo;
    }


    /**
     * 直接获取admin信息
     */
    @Override
    public AdminVo getInfo() {
        Admin admin = adminMapper.getAdmin(RoleConstant.ADMIN_ROLE);
        AdminVo adminVo = new AdminVo();
        BeanUtils.copyProperties(admin, adminVo);
        return adminVo;
    }
}
