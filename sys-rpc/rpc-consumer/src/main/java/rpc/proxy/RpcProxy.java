package rpc.proxy;

import com.alibaba.fastjson.JSON;
import rpc.client.NettyRpcClient;
import rpc.param.RpcRequest;
import rpc.param.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * @author: 橘子
 * @date: 2022/7/14 10:07
 */
public class RpcProxy {

    public static Object creatProxy(Class serviceClass){
        return Proxy.newProxyInstance(serviceClass.getClassLoader(), serviceClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RpcRequest rpcRequest = new RpcRequest();
                rpcRequest.setRequestId(UUID.randomUUID().toString());
                rpcRequest.setClassName(method.getDeclaringClass().getName());
                rpcRequest.setMethodName(method.getName());
                rpcRequest.setParamTypes(method.getParameterTypes());
                rpcRequest.setParamValues(args);
                NettyRpcClient rpcClient = new NettyRpcClient("127.0.0.1", 9999);
                //返回值都是序列化的字符串
                Object returnMsg = rpcClient.send(JSON.toJSONString(rpcRequest));
                RpcResponse rpcResponse = JSON.parseObject(returnMsg.toString(), RpcResponse.class);
                if (rpcResponse.getError() != null) {
                    System.out.println("rpc调用失败: " + rpcResponse.getError());
                    throw new RuntimeException(rpcResponse.getError());
                }
                System.out.println("client请求成功.." + JSON.toJSONString(rpcResponse));
                return JSON.parseObject(rpcResponse.getResult().toString(), method.getReturnType());
            }
        });
    }
}
