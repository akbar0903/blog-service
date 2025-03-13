package com.akbar.mapper;

import com.akbar.pojo.entity.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    void insertLog(Log log);

    @Select("select * from tb_log")
    List<Log> pageLog();

    @Delete("delete from tb_log where operated_time < now() - interval #{days} day")
    int deleteLogsOlderThan(int days);
}
