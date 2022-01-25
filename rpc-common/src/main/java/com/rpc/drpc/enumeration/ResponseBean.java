package com.rpc.drpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ResponseBean
 * @Description TODO
 * @Author hy-ha
 * @Date 2022/1/25 11:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> implements Serializable {
    private long code;
    private String message;
    private T obj;
    public static <T> ResponseBean<T> success(){
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(),ResponseBeanEnum.SUCCESS.getMessage(),null);
    }

    public static <T> ResponseBean<T> success(T obj){
        return new ResponseBean(ResponseBeanEnum.SUCCESS.getCode(), ResponseBeanEnum.SUCCESS.getMessage(), obj);
    }

    public static <T> ResponseBean<T> error(ResponseBeanEnum respBeanEnum){
        return new ResponseBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), null);
    }

    public static <T> ResponseBean<T> error(ResponseBeanEnum respBeanEnum,T obj){
        return new ResponseBean(respBeanEnum.getCode(), respBeanEnum.getMessage(), obj);
    }
}
