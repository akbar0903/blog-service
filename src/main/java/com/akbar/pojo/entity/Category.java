package com.akbar.pojo.entity;

import com.akbar.constant.TimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private Integer adminId;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime createdTime;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime updatedTime;
}
