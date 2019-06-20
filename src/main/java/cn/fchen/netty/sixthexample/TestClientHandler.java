package cn.fchen.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Classname TestClientHandler
 * @Description
 * @Date 2019/6/17 12:52
 * @Author by Fchen
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Data> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Data msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyDataInfo.Data data = null;
        if(0 == randomInt){
            data = MyDataInfo.Data.newBuilder().
                    setDataType(MyDataInfo.Data.DataType.PersonType).
                    setPerson(MyDataInfo.Person.newBuilder().setName("FC").
                              setAge(18).
                               setPhone("13820190521").build()).
                    build();
        }else if(1 == randomInt){
            data = MyDataInfo.Data.newBuilder().
                    setDataType(MyDataInfo.Data.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().setName("dag").
                            setAge(2).build()).
                    build();
        }else{
            data = MyDataInfo.Data.newBuilder().
                    setDataType(MyDataInfo.Data.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().setName("cat").
                            setAge(2).build()).
                    build();
        }
        ctx.channel().writeAndFlush(data);
    }
}
