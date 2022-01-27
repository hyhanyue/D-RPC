package com.drpc.rpc.server;

import com.drpc.rpc.client.entity.RPCRequest;
import com.drpc.rpc.client.entity.RPCResponse;
import com.drpc.rpc.enumeration.ResponseBeanEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @ClassName WorkerThread
 * @Description TODO
 * @Author hy-ha
 * @Date 2022/1/26 11:00
 */
public class RequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket socket;
    private Object service;

    public RequestHandler(Socket socket, Object service){
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RPCRequest rpcRequest = (RPCRequest)objectInputStream.readObject();
            Object returnObject = invokeMethod(rpcRequest);
            objectOutputStream.writeObject(RPCResponse.success(returnObject));
            objectOutputStream.flush();
        }catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e){
            logger.info("调用或发送时有错误发生：" + e);
        }
    }

    private Object invokeMethod(RPCRequest rpcRequest) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException{
        Class<?> clazz = Class.forName(rpcRequest.getInterfaceName());
        //判断是否为同一类型或存在父子、接口关系
        if(!clazz.isAssignableFrom(service.getClass())){
            return RPCResponse.fail(ResponseBeanEnum.ClASS_NOT_FOUND);
        }
        Method method;
        try{
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
        }catch (NoSuchMethodException e){
            return RPCResponse.fail(ResponseBeanEnum.METHOD_NOT_FOUND);
        }
        return method.invoke(service, rpcRequest.getParameters());
    }
}