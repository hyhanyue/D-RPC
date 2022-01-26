package com.drpc.rpc.client.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public enum ResponseBeanEnum {
    SUCCESS(200,"调用方法成功"),
    FAIL(500,"调用方法失败"),
    NOT_FOUND_METHOD(501,"未找到指定方法"),
    NOT_FOUND_CLASS(502,"未找到指定类");
    private final Integer code;
    private final String message;
}
