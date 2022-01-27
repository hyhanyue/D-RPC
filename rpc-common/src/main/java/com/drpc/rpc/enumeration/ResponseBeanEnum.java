package com.drpc.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public enum ResponseBeanEnum {
    //系统类异常
    SUCCESS(200,"调用方法成功"),
    FAIL(500,"调用方法失败"),
    METHOD_NOT_FOUND(500,"未找到指定方法"),
    ClASS_NOT_FOUND(500,"未找到指定类"),

    //服务异常
    SERVICE_INVOCATION_FAILURE(10040,"服务调用出现失败"),
    SERVICE_CAN_NOT_BE_NULL(10041,"注册的服务不能为空"),
    SERVICE_NOT_FOUND(10042,"找不到对应的服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE(10043,"注册的服务未实现接口");
    private final Integer code;
    private final String message;
}
