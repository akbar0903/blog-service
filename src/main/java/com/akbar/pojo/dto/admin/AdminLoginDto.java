package com.akbar.pojo.dto.admin;

import com.akbar.constant.MessageConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginDto {

    @NotBlank(message = MessageConstant.USERNAME_CANT_BE_EMPTY)
    private String username;

    // 只确保密码不为空就可以
    // 用户可能使用旧的弱密码
    @NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
    private String password;
}
