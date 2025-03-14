package com.akbar.handler;

import com.akbar.exception.BaseException;
import com.akbar.pojo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     */
    @ExceptionHandler(BaseException.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("业务异常信息：{}", e.getMessage(), e);
        return Result.error(e.getMessage());
    }


    /**
     * RequestBody + valid触发的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("校验失败：{}", e.getMessage());

        // 提取所有字段的错误信息
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        return Result.error(errorMessage);
    }
}
