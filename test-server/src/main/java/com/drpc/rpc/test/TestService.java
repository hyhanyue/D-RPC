package com.drpc.rpc.test;

import com.drpc.rpc.api.HelloService;
import com.drpc.rpc.server.RPCServer;

/**
 * @ClassName TestService
 * @Description TODO 服务器端
 * @Author hy-ha
 * @Date 2022/1/26 11:05
 */
public class TestService {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RPCServer rpcServer = new RPCServer();
        //注册HelloServiceImpl服务
        rpcServer.register(helloService, 8888);
    }
}
