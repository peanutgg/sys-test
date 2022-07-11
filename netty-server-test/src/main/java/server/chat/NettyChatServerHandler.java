package server.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 橘子
 * @date: 2022/7/11 10:32
 */
public class NettyChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static List<Channel> channelList = new ArrayList<>();

    /**
     * 通道就绪事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //保存客户端channel
        channelList.add(channel);
        System.out.println("[Server]:" + channel.remoteAddress().toString().substring(1) + "在线");
    }

    /**
     * 通道未就绪-channel下线事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //客户端下线，移除对应通道
        channelList.remove(channel);
        System.out.println("channel.remoteAddress().toString():" + channel.remoteAddress().toString());
        System.out.println("-------------------------");
        System.out.println("[Server]" + channel.remoteAddress().toString().substring(1) + "下线");
    }

    /**
     * 通道读取事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel currentChannel = ctx.channel();
        //给别人发消息
        for (Channel channel : channelList) {
            if (channel != currentChannel) {
                channel.writeAndFlush(currentChannel.remoteAddress().toString().substring(1) + "说：" + msg);
            }
        }
    }

    /**
     * 异常处理事件
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        channelList.remove(channel);
        System.out.println("[Server]" + channel.remoteAddress().toString().substring(1) + "异常");

    }
}
