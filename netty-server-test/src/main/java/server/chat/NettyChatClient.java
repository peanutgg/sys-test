package server.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author: 橘子
 * @date: 2022/7/11 11:00
 */
public class NettyChatClient {
    private String ip;
    private int port;

    public NettyChatClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        NioEventLoopGroup workerGroup = null;
        Bootstrap bootstrap = null;
        try {
            bootstrap = new Bootstrap();
            /*默认线程数 cup*2*/
            workerGroup = new NioEventLoopGroup();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class) 
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder())
                                    .addLast(new StringEncoder())
                                    .addLast(new NettyChatClientHandler());
                        }
                    });
            //连接server
            ChannelFuture clientFuture = bootstrap.connect(ip, port).sync();
            Channel channel = clientFuture.channel();
            System.out.println(channel.localAddress().toString().substring(1) + "----------启动");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
            //监听通道关闭
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyChatClient("127.0.0.1",9999).run();
    }
}
