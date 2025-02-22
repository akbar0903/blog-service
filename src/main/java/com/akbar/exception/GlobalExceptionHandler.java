package com.akbar.exception;

import com.akbar.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 创建日志记录器
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理重复键异常
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        // 错误输出到控制台
        LOGGER.error("添加项时发生错误: ", e);

        // 返回一个更易懂的错误信息
        return Result.error("该项目已存在，请尝试其他值！");
    }


    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidationException(MethodArgumentNotValidException e) {
        // 输出日志
        LOGGER.error("数据格式错误: {}", e.getMessage());

        // 返回给前端详细的错误信息
        return Result.error("数据格式错误，请重新输入！");
    }


    // 处理全局异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        // 错误输出到控制台
        LOGGER.error("全局异常捕获", e);
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败！");
    }
}
