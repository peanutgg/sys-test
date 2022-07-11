package server.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: 橘子
 * @date: 2022/7/11 10:15
 */
public class NettyChatServer {
    private int port;

    public NettyChatServer(int port) {
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        ServerBootstrap serverBootstrap;
        try {
            bossGroup = new NioEventLoopGroup(1);
            /*默认线程数 cup*2*/
            workerGroup = new NioEventLoopGroup();
            serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new StringDecoder())
                                .addLast(new StringEncoder())
                                    .addLast(new NettyChatServerHandler());
                        }
                    });
            //启动并绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            /*new channel futureListener*/
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("bind port success");
                    } else {
                        System.out.println("bind port failed");
                    }
                }
            });
            System.out.println("server started..");
            //监听通道关闭状态,并不是真正的关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyChatServer(9999).run();
    }
}
