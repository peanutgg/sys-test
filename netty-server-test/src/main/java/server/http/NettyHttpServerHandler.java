package server.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author: 橘子
 * @date: 2022/7/11 18:28
 */
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 通道读就绪事件
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpObject) {
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            System.out.println("浏览器请求路径：" + request.uri());

            //给浏览器进行响应
            ByteBuf byteBuf = Unpooled.copiedBuffer("Netty Server", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            //设置响应头
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8")
                    .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
            ctx.channel().writeAndFlush(response);
        }
    }
}
