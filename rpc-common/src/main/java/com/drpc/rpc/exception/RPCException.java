package com.drpc.rpc.exception;

import com.drpc.rpc.enumeration.ResponseBeanEnum;

/**
 * @ClassName RPCException
 * @Description TODO
 * @Author hy-ha
 * @Date 2022/1/26 18:20
 */
public class RPCException extends RuntimeException{
    public RPCException(ResponseBeanEnum responseBeanEnum,String message){
        super(responseBeanEnum.getMessage()+":"+message);
    }
    public RPCException(String message,Throwable cause){
        super(message, cause);
    }
    public RPCException(ResponseBeanEnum responseBeanEnum){
        super(responseBeanEnum.getMessage());
    }
}
