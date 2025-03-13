package com.akbar.aop;

import com.akbar.annotation.LogAnno;
import com.akbar.pojo.entity.Log;
import com.akbar.mapper.LogMapper;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    private final LogMapper logMapper;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public LogAspect(LogMapper logMapper, HttpServletRequest httpServletRequest) {
        this.logMapper = logMapper;
        this.httpServletRequest = httpServletRequest;
    }

    @Around("@annotation(com.akbar.annotation.LogAnno)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        // 获取调用方法的信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 管理员的id和operator，就是当前登录人的id和username
        Integer adminId = 9;
        String operator = "akbar";

        // 获取operationType
        LogAnno logAnnotation = method.getAnnotation(LogAnno.class);
        String operationType = logAnnotation.operationType();

        // 操作类的类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 操作结果
        // 调用原始目标方法
        long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        String returnValue = JSONObject.toJSONString(result);

        // 操作的执行耗时
        long costTime = endTime - beginTime;

        // user-agent
        String userAgent = httpServletRequest.getHeader("User-Agent");

        // ip地址
        String ipAddress = httpServletRequest.getRemoteAddr();

        // 保存日志
        Log log = new Log(null, operationType, className, methodName, methodParams, returnValue, costTime, operator, null, userAgent, ipAddress, adminId);
        logMapper.insertLog(log);

        return result;
    }
}
