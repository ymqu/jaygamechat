package com.jaygame.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * initialization: after channel register, the handlers will be added to it by initializing.
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //HttpServerCodec, netty provide handler which help server decode the request
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        //add custom handler to return "hello netty" to client
        pipeline.addLast("customHandler", new CustomHandler());
    }
}
