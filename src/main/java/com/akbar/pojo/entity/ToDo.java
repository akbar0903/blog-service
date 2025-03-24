package com.akbar.pojo.entity;

import com.akbar.constant.TimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDo {
    private Integer id;
    private String title;
    private String type;
    private Integer isCompleted;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime completedTime;
    @JsonIgnore
    private Integer adminId;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime createdTime;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime updatedTime;
}
