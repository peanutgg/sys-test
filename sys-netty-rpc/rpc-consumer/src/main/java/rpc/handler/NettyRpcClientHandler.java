package rpc.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @author: 橘子
 * @date: 2022/7/14 10:05
 *
 * *****handler 多个客户端 ，多个线程，多个请求，多个channel
 */
@Component
@Data
public class NettyRpcClientHandler extends SimpleChannelInboundHandler<String> implements Callable<String> {
    private ChannelHandlerContext ctx;
    private String reqMsg;
    /**
     * 通道就绪事件
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx=ctx;
    }

    /**
     * 通道读就绪事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("服务端返回消息..." + msg);
    }


    @Override
    public String call() throws Exception {

        return null;
    }
}
