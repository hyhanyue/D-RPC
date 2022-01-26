package com.drpc.rpc.server;

import com.drpc.rpc.client.entity.RPCRequest;
import com.drpc.rpc.client.entity.RPCResponse;
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
public class WorkerThread implements Runnable{
    private static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);

    private Socket socket;
    private Object service;

    public WorkerThread(Socket socket, Object service){
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RPCRequest rpcRequest = (RPCRequest)objectInputStream.readObject();
            //利用反射原理找到远程所需调用的方法
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            //invoke(obj实例对象,obj可变参数)
            Object returnObject = method.invoke(service, rpcRequest.getParameters());
            objectOutputStream.writeObject(RPCResponse.success(returnObject));
            objectOutputStream.flush();
        }catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
            logger.info("调用或发送时有错误发生：" + e);
        }
    }
}
