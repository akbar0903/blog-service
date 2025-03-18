package com.akbar.pojo.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageDto {
    private Integer pageNum;
    private Integer pageSize;
    private Integer categoryId;
    private Integer state;
    private String title;   // 可以根据标题模糊搜索
}
