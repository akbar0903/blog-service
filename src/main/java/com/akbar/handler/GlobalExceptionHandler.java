package com.akbar.handler;

import com.akbar.constant.MessageConstant;
import com.akbar.exception.BaseException;
import com.akbar.pojo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.sql.SQLIntegrityConstraintViolationException;
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


    /**
     * 捕获SQL异常
     * 比如key重复异常
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException e) {
        String message = e.getMessage();
        // 异常信息是这样的：Duplicate entry 'akbar' for key 'employee.idx_username'
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }

        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }


    /**
     * 捕获文件上传大小超限异常
     * 默认multipart上传文件大小不能超过1MB
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE) // 返回 413 状态码
    public Result<String> handleMaxUploadSizeExceeded(MaxUploadSizeExceededException e) {
        log.warn("文件上传大小超过限制：{}", e.getMessage());
        return Result.error(MessageConstant.FILE_SIZE_TOO_LARGE);
    }
}
