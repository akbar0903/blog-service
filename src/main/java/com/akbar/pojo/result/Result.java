package com.akbar.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回工具类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private Integer code;  // 1成功，0和其它数字为失败
    private String msg;  // //错误信息
    private T data;  // //数据

    public static final Integer SUCCESS_CODE = 1;
    public static final Integer ERROR_CODE = 0;

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, "Success", data);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(SUCCESS_CODE, message, null);
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "Success", null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR_CODE, message, null);
    }
}
