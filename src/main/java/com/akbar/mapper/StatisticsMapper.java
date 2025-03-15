package com.akbar.mapper;

import com.akbar.pojo.vo.StatisticsVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {
    StatisticsVO getCounts();
}
