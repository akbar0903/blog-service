package com.akbar.pojo.dto.admin;

import com.akbar.constant.MessageConstant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateDto {
    private Integer id;
    private String name;
    @URL(message = MessageConstant.INVALID_URL)
    private String avatar;
    @Email(message = MessageConstant.INVALID_EMAIL)
    private String email;
    @Size(min = MessageConstant.MIN_QQ_NUMBER_LENGTH, max = MessageConstant.MAX_QQ_NUMBER_LENGTH,message = MessageConstant.INVALID_QQ_NUMBER)
    private String qqNumber;
    private String address;
    @URL(message = MessageConstant.INVALID_URL)
    private String githubUrl;
    @URL(message = MessageConstant.INVALID_URL)
    private String bilibiliUrl;
    @URL(message = MessageConstant.INVALID_URL)
    private String giteeUrl;
}
