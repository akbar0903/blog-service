package com.akbar.service.impl;

import com.akbar.mapper.*;
import com.akbar.pojo.vo.StatisticsVO;
import com.akbar.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;


    /**
     * 获取统计信息
     */
    @Override
    public StatisticsVO getStatistics() {
        return statisticsMapper.getCounts();
    }
}
