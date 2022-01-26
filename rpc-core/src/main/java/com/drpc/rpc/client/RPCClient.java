package com.drpc.rpc.client;

import com.drpc.rpc.client.entity.RPCRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @ClassName RPCClient
 * @Description TODO 进行远程调用的客户端
 * @Author hy-ha
 * @Date 2022/1/25 18:26
 */
public class RPCClient {
    private static final Logger logger = LoggerFactory.getLogger(RPCClient.class);
    public Object sendRequest(RPCRequest rpcRequest,String host,int port){
        /**
         * socket套接字实现TCP网络传输
         * try()中一般放对资源的申请，若{}出现异常，()资源会自动关闭
         */
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：" + e);
            return null;
        }
    }
}
