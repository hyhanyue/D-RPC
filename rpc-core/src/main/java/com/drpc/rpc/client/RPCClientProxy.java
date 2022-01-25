package com.drpc.rpc.client;

import com.drpc.rpc.client.entity.RPCRequest;
import com.drpc.rpc.client.entity.RPCResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName RPCClientProxy
 * @Description TODO
 * @Author hy-ha
 * @Date 2022/1/25 15:09
 */
public class RPCClientProxy implements InvocationHandler {
    private String host;
    private int port;

    public RPCClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //抑制编译器产生警告信息
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz){
        //创建代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //客户端向服务端传输的对象,Builder模式生成
        RPCRequest rpcRequest = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        //进行远程调用的客户端
        RPCClient rpcClient = new RPCClient();
        return ((RPCResponse) rpcClient.sendRequest(rpcRequest, host, port));
    }
}
