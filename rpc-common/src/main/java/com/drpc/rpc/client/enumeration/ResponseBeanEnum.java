package com.drpc.rpc.client.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseBeanEnum {
    SUCCESS(200,"");
    private final Integer code;
    private final String message;
}
