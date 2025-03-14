package com.akbar.aop;

import com.akbar.annotation.AutoFill;
import com.akbar.constant.AutoFillConstant;
import com.akbar.context.BaseContext;
import com.akbar.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class AutoFillAspect {


     // 用 Map 缓存 Method 对象，避免重复反射
    private static final Map<String, Method> METHOD_CACHE = new HashMap<>();

    /**
     * 切入点
     * 拦截所有mapper类中所有方法 当且仅当有@AutoFill注解的方法
     */
    @Pointcut("execution(* com.akbar.mapper.*.*(..)) && @annotation(com.akbar.annotation.AutoFill)")
    public void autoFillPointCut() {}


    /**
     * 前置通知
     * mapper中的sql执行之前执行
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        // 获取当前拦截的方法的操作类型(方法签名)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); //获取方法签名
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);  //获取该方法上的注解对象
        OperationType operationType = autoFill.value();//获取数据库操作类型

        // 获取当前被拦截的方法的参数
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return;
        }
        Object entity = args[0];


        // 调用invokeMethod，对entity进行赋值操作
        if (operationType == OperationType.INSERT) {
            try {
                invokeMethod(entity, AutoFillConstant.SET_CREATED_TIME, LocalDateTime.class, LocalDateTime.now());
                invokeMethod(entity, AutoFillConstant.SET_UPDATED_TIME, LocalDateTime.class, LocalDateTime.now());
                invokeMethod(entity, AutoFillConstant.SET_ADMIN_ID, Integer.class, BaseContext.getCurrentAdminId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationType == OperationType.UPDATE) {
            try {
                invokeMethod(entity, AutoFillConstant.SET_UPDATED_TIME, LocalDateTime.class, LocalDateTime.now());
                invokeMethod(entity, AutoFillConstant.SET_ADMIN_ID, Integer.class, BaseContext.getCurrentAdminId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 封装方法调用逻辑，使用缓存
     * @param entity 实体类的实例（比如，User的实例就是user对象）
     *               entity.getClass()返回对象的Class类，比如对象是user，返回的是User类
     *               entity.getClass().getName()返回全限定类名，比如com.akbar.entity.User
     *              entity.getClass().getName() + "." + methodName形成一个唯一的标识
     *               比如：com.akbar.entity.User.setCreatedTime
     * @param methodName 方法名，比如setCreatedTime
     * @param paramType 方法类型，比如LocalDateTime.class
     * @param value 要赋的值，比如LocalDateTime.now()
     */
    private void invokeMethod(Object entity, String methodName,Class<?> paramType,  Object value) throws Exception {
        String cacheKey = entity.getClass().getName() + "." + methodName;
        Method method = METHOD_CACHE.get(cacheKey);

        // 如果缓存中没有，就反射获取并存入缓存
        if (method == null) {
            method = entity.getClass().getDeclaredMethod(methodName, paramType);
            METHOD_CACHE.put(cacheKey, method);
        }

        // 调用方法
        method.invoke(entity, value);
    }
}
