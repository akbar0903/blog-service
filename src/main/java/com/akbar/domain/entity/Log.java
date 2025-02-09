package com.akbar.domain.entity;

import java.time.LocalDateTime;

public class Log {
    private Integer id;
    private String operationType;
    private String className;
    private String methodName;
    private String methodParams;
    private String returnValue;
    private Long costTime;
    private String operator;
    private LocalDateTime operatedTime;
    private String userAgent;
    private String ipAddress;
    private Integer adminId;

    public Log(Integer id, String operationType, String className, String methodName, String methodParams, String returnValue, Long costTime, String operator, LocalDateTime operatedTime, String userAgent, String ipAddress, Integer adminId) {
        this.id = id;
        this.operationType = operationType;
        this.className = className;
        this.methodName = methodName;
        this.methodParams = methodParams;
        this.returnValue = returnValue;
        this.costTime = costTime;
        this.operator = operator;
        this.operatedTime = operatedTime;
        this.userAgent = userAgent;
        this.ipAddress = ipAddress;
        this.adminId = adminId;
    }

    public Log() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationType() {
        return operationType;
    }
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodParams() {
        return methodParams;
    }
    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }

    public String getReturnValue() {
        return returnValue;
    }
    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public Long getCostTime() {
        return costTime;
    }
    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }

    public LocalDateTime getOperatedTime() {
        return operatedTime;
    }
    public void setOperatedTime(LocalDateTime operatedTime) {
        this.operatedTime = operatedTime;
    }

    public String getUserAgent() {
        return userAgent;
    }
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getAdminId() {
        return adminId;
    }
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", operationType='" + operationType + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", methodParams='" + methodParams + '\'' +
                ", returnValue='" + returnValue + '\'' +
                ", costTime=" + costTime +
                ", operator='" + operator + '\'' +
                ", operatedTime=" + operatedTime +
                ", userAgent='" + userAgent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}
