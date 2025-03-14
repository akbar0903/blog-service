package com.akbar.exception;

/**
 * 拒绝删除异常
 */
public class DeletionNotAllowedException extends BaseException {
    public DeletionNotAllowedException() {
    }

    public DeletionNotAllowedException(String message) {
        super(message);
    }
}
