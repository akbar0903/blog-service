package com.akbar.controller;

import com.akbar.pojo.result.Result;
import com.akbar.pojo.vo.StatisticsVo;
import com.akbar.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    /**
     * 获取统计信息统计信息
     */
    @GetMapping
    public Result<StatisticsVo> getStatistics() {
        StatisticsVo statisticsVO = statisticsService.getStatistics();
        return Result.success(statisticsVO);
    }
}
