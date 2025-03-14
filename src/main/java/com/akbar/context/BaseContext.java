package com.akbar.context;

/**
 * ThreadLocal存储当前登录用户的信息
 */
public class BaseContext {
    public static final ThreadLocal<Integer> adminIdHolder = new ThreadLocal<>();
    public static final ThreadLocal<String> roleHolder = new ThreadLocal<>();

    public static void setCurrentAdminId(Integer id) {
        adminIdHolder.set(id);
    }
    public static Integer getCurrentAdminId() {
        return adminIdHolder.get();
    }

    public static void setCurrentAdminRole(String role) {
        roleHolder.set(role);
    }
    public static String getCurrentAdminRole() {
        return roleHolder.get();
    }

    public static void clear() {
        adminIdHolder.remove();
        roleHolder.remove();
    }
}
