package com.drpc.rpc.client;

import com.drpc.rpc.client.entity.RPCRequest;
import com.drpc.rpc.client.entity.RPCResponse;
import com.drpc.rpc.enumeration.ResponseBeanEnum;
import com.drpc.rpc.exception.RPCException;
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
    public Object sendRequest(RPCRequest rpcRequest, String host, int port) {
        /**
         * socket套接字实现TCP网络传输
         * try()中一般放对资源的申请，若{}出现异常，()资源会自动关闭
         */
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            RPCResponse rpcResponse = (RPCResponse)objectInputStream.readObject();
            if(rpcResponse == null){
                logger.error("服务调用失败1，service:{}" + rpcRequest.getInterfaceName());
                throw new RPCException(ResponseBeanEnum.SERVICE_INVOCATION_FAILURE, "service:" + rpcRequest.getInterfaceName());
            }
            if(rpcResponse.getStatusCode() == null || !rpcResponse.getStatusCode().equals(ResponseBeanEnum.SUCCESS.getCode())){
                logger.error("服务调用失败2，service:{} response:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RPCException(ResponseBeanEnum.SERVICE_INVOCATION_FAILURE, "service:" + rpcRequest.getInterfaceName());
            }
            return rpcResponse.getData();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生3：" + e);
            throw new RPCException("服务调用失败4：", e);
        }
    }
}
