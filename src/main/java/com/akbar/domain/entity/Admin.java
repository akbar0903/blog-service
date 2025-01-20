package com.akbar.domain.entity;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String nickname;
    private String avatar;
    private String email;
    private String githubUrl;
    private String bilibiliUrl;
    private String giteeUrl;
    private LocalDateTime createdTime;
}
