package com.akbar.pojo.vo;

import com.akbar.constant.TimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleVo {
    private Integer id;
    private String title;
    private String summary;
    private String content;
    private String coverImage;
    private Integer state;
    private String categoryName;
    private List<String> tagNames;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime createdTime;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime updatedTime;
}
