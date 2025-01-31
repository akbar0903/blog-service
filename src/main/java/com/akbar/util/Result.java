package com.akbar.util;

public class Result<T> {
    // 1代表成功，0代表失败
    private Integer code;
    private String msg;
    private T data;

    public Result() {}

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    // 带data
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    // 不带data
    public static Result<Void> success(String msg) {
        return new Result<>(1, msg, null);
    }

    // 错误
    public static Result<Void> error(String msg) {
        return new Result<>(0, msg, null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
