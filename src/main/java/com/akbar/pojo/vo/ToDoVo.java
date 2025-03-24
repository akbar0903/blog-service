package com.akbar.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoVo {
    private String title;
    private String type;
    private String description;
    private Integer isCompleted;
}
