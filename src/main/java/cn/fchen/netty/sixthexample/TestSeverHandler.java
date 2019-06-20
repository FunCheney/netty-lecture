package cn.fchen.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Classname TestSeverHandler
 * @Description
 * @Date 2019/6/17 12:31
 * @Author by Fchen
 */
public class TestSeverHandler extends SimpleChannelInboundHandler<MyDataInfo.Data> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Data msg) throws Exception {
        MyDataInfo.Data.DataType dataType = msg.getDataType();
        if(dataType == MyDataInfo.Data.DataType.PersonType){
            MyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getName());
            System.out.println(person.getAge());
        }else if(dataType == MyDataInfo.Data.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getName());
        }else{
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getName());
        }
    }
}
