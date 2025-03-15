package com.akbar.pojo.dto.admin;

import com.akbar.annotation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordEditDto {
    private Integer id;

    @ValidPassword
    private String oldPassword;

    @ValidPassword
    private String newPassword;

    @ValidPassword
    private String confirmPassword;
}
