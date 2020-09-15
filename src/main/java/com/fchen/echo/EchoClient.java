package com.fchen.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: Fchen
 * @date: 2020/9/15 10:56 下午
 * @desc: 客户端引导类
 */
public class EchoClient {
    private final String host;

    private final int port;

    public EchoClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient("127.0.0.1", 8888).start();
    }

    public void start() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            // 创建 BootStrap
            Bootstrap b = new Bootstrap();
            // 指定 EventLoopGroup 已处理客户端事件;需要适用于 NIO 实现
            b.group(group)
                    // 适用于 NIO 传输的 Channel 类型
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    // 在创建 Channel 时，向 ChannelPipeline 中添加一个 EchoClientHandler 实例
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                           ch.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            // 连接到远程节点，阻塞等待直到连接完成
            ChannelFuture f = b.connect().sync();
            // 阻塞，直到 Channel 关闭
            f.channel().closeFuture().sync();
        } finally {
            // 关闭线程池并且释放所有资源
            group.shutdownGracefully().sync();
        }
    }
}
