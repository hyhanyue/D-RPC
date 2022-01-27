package com.drpc.test;

import com.drpc.rpc.api.HelloObject;
import com.drpc.rpc.api.HelloService;
import com.drpc.rpc.client.RPCClientProxy;

/**
 * @ClassName TestClient
 * @Description TODO 客户端
 * @Author hy-ha
 * @Date 2022/1/26 11:04
 */
public class TestClient {
    public static void main(String[] args) {
        //接口与代理对象之间的中介对象
        RPCClientProxy proxy = new RPCClientProxy("127.0.0.1", 8888);
        //创建代理对象
        HelloService helloService = proxy.getProxy(HelloService.class);
        //接口方法的参数对象
        HelloObject object = new HelloObject(18, "Hello World");
        //由动态代理可知，代理对象调用hello()实际会执行invoke()
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
