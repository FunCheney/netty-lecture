package com.fchen.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: Fchen
 * @date: 2020/9/15 10:11 下午
 * @desc:
 *        针对不同类型的事件来调用 ChannelHandler
 *        应用程序通过实现或者扩展 ChannelHandler 来挂钩到事件的生命周期，并且提供自定义的生命逻辑
 *        在架构上，ChannelHandler 有助于保持业务逻辑与网络处理代码的分离。这简化了开发过程，因为
 *         代码必须不断的演化以响应不断变化的需求
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 对于每个传入的消息都要调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        // 将消息记录到控制台
        System.out.println("Server received：" + in.toString(CharsetUtil.UTF_8));

        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将未决消息冲刷到远程节点，并且关闭该 Channel
       ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常栈
        cause.printStackTrace();
        // 关闭改 Channel
        ctx.close();
    }
}
