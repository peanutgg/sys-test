package rpc.provider.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import rpc.param.RpcRequest;
import rpc.param.RpcResponse;
import rpc.provider.anno.RpcService;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 橘子
 * @date: 2022/7/12 11:31
 */
@Component
public class NettyServerHandler extends SimpleChannelInboundHandler<String> implements ApplicationContextAware {
    private static final Map SERVICE_NAME_MAP = new ConcurrentHashMap();

    /**
     * 通道读就绪事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        RpcResponse rpcResponse = new RpcResponse();
        RpcRequest rpcRequest = JSON.parseObject(msg, RpcRequest.class);
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        try {
            rpcResponse.setResult(handler(rpcRequest));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            rpcResponse.setError(e.getMessage());
        }
        //给客户端响应
        ctx.writeAndFlush(JSON.toJSONString(rpcResponse));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceMap = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (serviceMap != null && serviceMap.size() > 0) {
            Set<Map.Entry<String, Object>> entries = serviceMap.entrySet();
            for (Map.Entry<String, Object> item : entries) {
                Object serviceBean = item.getValue();
                if (serviceBean.getClass().getInterfaces().length == 0) {
                    throw new RuntimeException("服务必须实现接口");
                }
                SERVICE_NAME_MAP.put(serviceBean.getClass().getInterfaces()[0].getName(), serviceBean);
            }
        }
    }

    public Object handler(RpcRequest rpcRequest) throws InvocationTargetException {
        //根据classname找到对应的bean
        Object serviceBean = SERVICE_NAME_MAP.get(rpcRequest.getClassName());
        if (serviceBean == null) {
            throw new RuntimeException("根据beanName找不到服务:" + rpcRequest.getClassName());
        }
        //解析bean相关信息
        Class<?> serviceBeanClass = serviceBean.getClass();
        //找到method方法 执行对应的方法
        String methodName = rpcRequest.getMethodName();
        Class<?>[] paramTypes = rpcRequest.getParamTypes();
        Object[] parameters = rpcRequest.getParamValues();
        //反射找对对应的bean的方法，CGLIB反射调用
        FastClass proxyServiceBean = FastClass.create(serviceBeanClass);
        FastMethod proxyMethod = proxyServiceBean.getMethod(methodName, paramTypes);
        return proxyMethod.invoke(serviceBean, parameters);
    }
}
