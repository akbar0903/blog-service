package com.akbar.pojo.dto.article;

import com.akbar.constant.ArticlePageConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageDto {
    private Integer pageNum = ArticlePageConstant.DEFAULT_PAGE_NUM;
    private Integer pageSize = ArticlePageConstant.DEFAULT_PAGE_SIZE;
    private Integer categoryId;
    private Integer state;
    private String title;   // 可以根据标题模糊搜索
}
