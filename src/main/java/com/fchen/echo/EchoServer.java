package com.fchen.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: Fchen
 * @date: 2020/9/15 10:26 下午
 * @desc:
 *      1.绑定到服务器将在其上监听并接受传入连接请求的端口
 *      2.配置 Channel，已将有段的入站消息通知给 EchoServerHandler 实例
 *
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {

        new EchoServer(8888).start();
    }

    public void start() throws Exception{
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 创建 EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 创建 ServerBootstrap 引导和绑定服务器
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(group)
                    // 指定所使用的的 NIO 传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // 使用指定的端口设置套接字地址
                    .localAddress(new InetSocketAddress(port))
                    // 添加一个 EchoServerHandler 到子 Channel 的 ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步绑定服务器；调用sync() 方法阻塞等待直到绑定完成
            ChannelFuture f = b.bind().sync();
            // 获取 Channel 的 CloseFuture，并且阻塞当前线程直到它完成
            f.channel().closeFuture().sync();
        } finally {
            // 关闭 EventLoopGroup 释放所有资源
            group.shutdownGracefully().sync();
        }
    }

}
