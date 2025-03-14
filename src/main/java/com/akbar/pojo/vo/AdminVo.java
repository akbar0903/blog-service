package com.akbar.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVo {
    private Integer id;
    private String username;
    private String name;
    private String role;
    private String avatar;
    private String email;
    private String qqNumber;
    private String address;
    private String githubUrl;
    private String bilibiliUrl;
    private String giteeUrl;
}
