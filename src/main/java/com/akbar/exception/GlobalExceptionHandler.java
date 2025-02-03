package com.akbar.exception;

import com.akbar.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理 DuplicateKeyException
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        // 错误输出到控制台
        LOGGER.error("添加项时发生错误: ", e);

        // 返回一个更易懂的错误信息
        return Result.error("无法完成操作，指定的项已被占用，请尝试其他。");
    }

    // 处理全局异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 错误输出到控制台
        LOGGER.error("全局异常捕获", e);
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败！");
    }
}
