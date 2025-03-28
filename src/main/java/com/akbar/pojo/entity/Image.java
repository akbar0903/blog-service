package com.akbar.pojo.entity;

import com.akbar.constant.TimeFormatConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    private Integer id;
    private String url;
    private String objectName;
    @JsonIgnore
    private Integer adminId;
    @JsonFormat(pattern = TimeFormatConstant.DATE_TIME_PATTERN)
    private LocalDateTime uploadTime;
}
