package rpc.provider.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import rpc.provider.anno.RpcService;

import java.util.Map;

/**
 * @author: 橘子
 * @date: 2022/7/12 11:31
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> implements ApplicationContextAware {

    /**
     * 通道读就绪事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> rpcServiceBeanMap = applicationContext.getBeansWithAnnotation(RpcService.class);
    }
}
