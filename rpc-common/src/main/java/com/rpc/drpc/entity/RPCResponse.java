package com.rpc.drpc.entity;

import com.rpc.drpc.enumeration.ResponseBeanEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName RPCResponse
 * @Description TODO 服务端处理完后，向客户端返回的对象
 * @Author hy-ha
 * @Date 2022/1/25 10:48
 */
@Data
public class RPCResponse<T> implements Serializable {
    /**
     *响应状态码
     */
    private Integer statusCode;
    /**
     *响应状态码对应的信息
     */
    private String message;
    /**
     *成功时的响应数据
     */
    private T data;

    public static <T> RPCResponse<T> success(T data){
        RPCResponse<T> response = new RPCResponse<>();
        response.setStatusCode(ResponseBeanEnum.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RPCResponse<T> error(ResponseBeanEnum code){
        RPCResponse<T> response = new RPCResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }



}
