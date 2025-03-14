package com.akbar.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdateDto {
    private Integer id;
    private String name;
    private String avatar;
    private String email;
    private String qqNumber;
    private String address;
    private String githubUrl;
    private String bilibiliUrl;
    private String giteeUrl;
}
