package server.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author: 橘子
 * @date: 2022/7/11 18:24
 */
public class NettyHttpServer {
    private String ip;
    private int port;

    public NettyHttpServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup boosGroup = null;
        NioEventLoopGroup workerGroup = null;
        ServerBootstrap bootstrap = null;
        try {
            boosGroup = new NioEventLoopGroup(1);
            workerGroup = new NioEventLoopGroup();
            bootstrap = new ServerBootstrap();

            bootstrap.group(boosGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec())
                                    .addLast(new NettyHttpServerHandler());
                        }
                    });

            ChannelFuture future = bootstrap.bind(ip, port).sync();
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("netty server bind success");
                    } else {
                        System.out.println("netty server bind failed");
                    }
                }
            });
            System.out.println("server started");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyHttpServer("127.0.0.1", 8080).run();
    }
}
