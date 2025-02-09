package com.akbar.service.impl;

import com.akbar.domain.entity.Log;
import com.akbar.domain.vo.PageBean;
import com.akbar.mapper.LogMapper;
import com.akbar.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private final LogMapper logMapper;
    @Autowired
    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public PageBean getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Log> logs = logMapper.pageLog();
        Page<Log> page = (Page<Log>) logs;
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public boolean cleanLogs(int days) {
        int result = logMapper.deleteLogsOlderThan(days);
        return result > 0;
    }

    // 每天凌晨3点自动清理超过30天的日志
    @Scheduled(cron = "0 0 3 * * ?")
    @Override
    public void autoCleanLogs() {
        logMapper.deleteLogsOlderThan(30);
    }
}
