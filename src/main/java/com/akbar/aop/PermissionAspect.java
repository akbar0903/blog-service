package com.akbar.aop;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RoleConstant;
import com.akbar.context.BaseContext;
import com.akbar.exception.PermissionDeniedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 权限控制切面类
 */
@Component
@Aspect
public class PermissionAspect {

    // 限制切点到 com.akbar.service 包及其子包下的 @RequiresAdmin 注解方法
    @Around("execution(* com.akbar.service..*.*(..)) && @annotation(com.akbar.annotation.RequiresAdmin)")
    public Object checkAdminPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        String role = BaseContext.getCurrentAdminRole();

        // role=null就说明没有携带jwt，没有携带jwt就是没有登录
        if (role == null) {
            throw new PermissionDeniedException(MessageConstant.USER_NOT_LOGIN);
        }

        if (!RoleConstant.ADMIN_ROLE.equals(role)) {
            throw new PermissionDeniedException(MessageConstant.PERMISSION_DENIED);
        }
        return joinPoint.proceed();
    }
}
