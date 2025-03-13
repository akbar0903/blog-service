package com.akbar.context;

/**
 * ThreadLocal存储当前登录用户的信息
 */
public class BaseContext {
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Integer id) {
        threadLocal.set(id);
    }

    public static Integer getCurrentId() {
        return threadLocal.get();
    }

    public static void clearCurrentId() {
        threadLocal.remove();
    }
}
