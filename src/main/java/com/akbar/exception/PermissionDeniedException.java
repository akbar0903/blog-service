package com.akbar.exception;

/**
 * 没有权限，拒绝操作异常类
 */
public class PermissionDeniedException extends BaseException {
    public PermissionDeniedException() {
    }

    public PermissionDeniedException(String message) {
        super(message);
    }
}
