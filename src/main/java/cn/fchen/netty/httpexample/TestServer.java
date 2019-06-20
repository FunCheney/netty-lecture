package cn.fchen.netty.httpexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {
    public static void main(String[] args) throws Exception{
        /**
         * 线程组 从客户端接收连接 但不处理
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        /**
         * 线程组 处理获取的连接，将结果返回给客户端
         */
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            /**
             * 服务端启动类
             */
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());

            ChannelFuture sync = serverBootstrap.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
