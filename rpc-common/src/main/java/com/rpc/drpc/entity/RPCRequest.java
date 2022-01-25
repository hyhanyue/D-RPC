package com.rpc.drpc.entity;

import java.io.Serializable;

/**
 * @ClassName RPCRequest
 * @Description TODO 传输格式（传输协议）：客户端向服务端传输的对象
 * @Author hy-ha
 * @Date 2022/1/25 10:46
 */
public class RPCRequest implements Serializable {
    /**
     * 待调用接口名称
     */
    private String interfaceName;
    /**
     * 待调用方法名称
     */
    private String methodName;
    /**
     * 待调用方法的参数
     */
    private Object[] parameters;
    /**
     * 待调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
