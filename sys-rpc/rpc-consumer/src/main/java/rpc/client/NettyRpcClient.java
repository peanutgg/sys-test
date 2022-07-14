package rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: 橘子
 * @date: 2022/7/14 10:05
 */
public class NettyRpcClient {
    private String ip;
    private int port;
    private NioEventLoopGroup workerGroup;
    private Bootstrap clientBootStrap;

    public NettyRpcClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        try {
            workerGroup = new NioEventLoopGroup();
            clientBootStrap = new Bootstrap();
            clientBootStrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(null);
                            // TODO: 2022/7/14 add client Handler
                        }
                    });
            //连接
            ChannelFuture clientFuture = clientBootStrap.connect(ip, port).sync();
            System.out.println("rpc client started...");
            //监听通道关闭事件
            clientFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        }
    }

    public void close() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
    
}
