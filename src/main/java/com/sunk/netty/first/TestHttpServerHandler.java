package com.sunk.netty.first;

import com.sun.glass.ui.Clipboard;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 通道处理器
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest) {
            ByteBuf resultContent = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, resultContent);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, Clipboard.TEXT_TYPE);
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, resultContent.readableBytes());
            ctx.writeAndFlush(response);
        }

    }
}
