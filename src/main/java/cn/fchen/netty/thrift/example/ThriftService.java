package cn.fchen.netty.thrift.example;

import cn.fchen.netty.thrift.Person;
import cn.fchen.netty.thrift.PersonServcie;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

/**
 * @Classname ThriftService
 * @Description 服务
 * @Date 2019/6/29 14:00
 * @Author by Fchen
 */
public class ThriftService {
    public static void main(String[] args) throws Exception{
        TNonblockingServerSocket socket = new TNonblockingServerSocket(6969);

        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

        PersonServcie.Processor<PersonServiceImpl> processor = new PersonServcie.Processor<>(new PersonServiceImpl());
        //协议层
        arg.protocolFactory(new TCompactProtocol.Factory());
        //传输层
        arg.transportFactory(new TFastFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("Thrift Server Started");
        server.serve();
    }
}
