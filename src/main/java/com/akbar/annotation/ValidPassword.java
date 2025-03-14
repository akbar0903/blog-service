package com.akbar.annotation;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RegexConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 密码校验注解
 */
@Target(ElementType.FIELD)  // 使用与类中的成员变量
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
@Pattern(regexp = RegexConstant.PASSWORD_PATTERN, message = MessageConstant.PASSWORD_TOO_SHORT)
public @interface ValidPassword {
    String message() default "Invalid password"; // 默认消息，可被覆盖
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
