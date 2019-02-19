package com.jaygame.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * create own handler
 */

//SimpleChannelInboundHandler.
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        Channel channel = ctx.channel();
        System.out.println( channel.remoteAddress());

        //define sent msg:"hello netty" and put it into buffer.
        ByteBuf content = Unpooled.copiedBuffer("Hello netty", CharsetUtil.UTF_8);

        //construct http response to client
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

        //reponse with type and length
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

        ctx.writeAndFlush(response);
    }
}
