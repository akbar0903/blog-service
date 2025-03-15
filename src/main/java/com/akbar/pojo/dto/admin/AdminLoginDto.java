package com.akbar.pojo.dto.admin;

import com.akbar.annotation.ValidPassword;
import com.akbar.constant.MessageConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminLoginDto {

    @NotBlank(message = MessageConstant.USERNAME_CANT_BE_EMPTY)
    private String username;

    @ValidPassword
    private String password;
}
