package com.akbar.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 给前端返回统计数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsVO {
    private Integer articleCount;
    private Integer categoryCount;
    private Integer tagCount;
    private Integer ImageCount;
}
