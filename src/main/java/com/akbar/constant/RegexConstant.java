package com.akbar.constant;

/**
 * 正则表达式常量类
 * 密码至少8位，包含1个大写字母、1个小写字母、1个数字和1个特殊字符
 */
public class RegexConstant {
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
}
