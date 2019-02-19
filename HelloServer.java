package com.jaygame.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * client sent request, and server will reponse "hello netty"
 */
public class HelloServer {

    public static void main(String[] args) throws InterruptedException {

        //define  2 thread pools, bossGroup and workGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        //netty server create
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)   //set thread pools
                    .channel(NioServerSocketChannel.class)  //set nio channel
                    .childHandler(new HelloServerInitializer());        //define workGroup handler

            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
