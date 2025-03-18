package com.akbar.mapper;

import com.akbar.pojo.vo.StatisticsVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {
    StatisticsVo getCounts();
}
