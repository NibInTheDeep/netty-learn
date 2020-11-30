package com.sunk.netty.thirdexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class MyChatServer {

    public static void main(String[] args) {
        //请求接受处理线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //请求处理线程池
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            //启动组
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //group方法为bootstrap设置acceptor的EventLoopGroup和client的EventLoopGroup
            //定义好通道
            //定义子处理器
            serverBootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());
            //绑定端口  ,开始接收进来的连接
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
