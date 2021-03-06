package cn.fchen.netty.socketexample.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
/**
 * @Classname MyServer
 * @Description 服务器端
 * @Date 2019/5/16 12:48
 * @Author by Fchen
 */
public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup  = new NioEventLoopGroup();
        EventLoopGroup workerGroup  = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerInitializer());
            ChannelFuture sync = bootstrap.bind(6999).sync();
            sync.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
