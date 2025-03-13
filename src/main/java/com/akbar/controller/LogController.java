package com.akbar.controller;

import com.akbar.pojo.vo.PageBean;
import com.akbar.service.LogService;
import com.akbar.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
public class LogController {

    private final LogService logService;
    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public Result<PageBean> getLogs(@RequestParam(defaultValue = "1") int pageNum,
                                    @RequestParam(defaultValue = "10") int pageSize) {
        return Result.success(logService.getLogs(pageNum, pageSize));
    }

    // 按指定天数清理日志
    @PostMapping
    public Result<Void> cleanLogs(@RequestParam(defaultValue = "30") int days) {
        boolean result = logService.cleanLogs(days);
        if (!result) {
            return Result.error("日志清理失败！");
        }
        return Result.success("日志清理成功！");
    }
}
