package com.akbar.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    private String title;
    private String summary;
    private String content;
    private String coverImage;
    private Integer state;           // '1'：发布,'0'：草稿
    private Integer adminId;
    private Integer categoryId;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
