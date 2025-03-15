package com.akbar.aop;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RoleConstant;
import com.akbar.context.BaseContext;
import com.akbar.exception.PermissionDeniedException;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 权限控制切面类
 */
@Slf4j
@Component
@Aspect
@Size
public class PermissionAspect {

    // 限制切点到 com.akbar.service 包及其子包下的 @RequiresAdmin 注解方法
    @Before("@annotation(com.akbar.annotation.RequiresAdmin)")
    public void checkAdminPermission() {
        String role = BaseContext.getCurrentAdminRole();

        log.info("当前登录用户的角色: {}", role);

        // role=null就说明没有携带jwt，没有携带jwt就是没有登录
        if (role == null) {
            throw new PermissionDeniedException(MessageConstant.USER_NOT_LOGIN);
        }

        if (!RoleConstant.ADMIN_ROLE.equals(role)) {
            throw new PermissionDeniedException(MessageConstant.PERMISSION_DENIED);
        }
    }
}
