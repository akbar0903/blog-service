package com.akbar.service;

import com.akbar.pojo.vo.PageBean;

public interface LogService {
    PageBean getLogs(int pageNum, int pageSize);

    boolean cleanLogs(int days);

    // 每天凌晨3点自动清理超过30天的日志
    void autoCleanLogs();
}
