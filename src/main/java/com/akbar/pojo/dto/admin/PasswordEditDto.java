package com.akbar.pojo.dto.admin;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RegexConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordEditDto {
    private Integer id;

    // 旧密码本质上不需要满足新密码的安全规则
    @NotBlank(message = MessageConstant.OLD_PASSWORD_CANT_BE_EMPTY)
    private String oldPassword;

    @NotBlank(message = MessageConstant.NEW_PASSWORD_CANT_BE_EMPTY)
    @Pattern(regexp = RegexConstant.PASSWORD_PATTERN, message = MessageConstant.PASSWORD_TOO_WEAK)
    private String newPassword;

    // 确认密码唯一作用是 必须等于 newPassword
    private String confirmPassword;
}
