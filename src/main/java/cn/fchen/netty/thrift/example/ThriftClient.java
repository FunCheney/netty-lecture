package cn.fchen.netty.thrift.example;

import cn.fchen.netty.thrift.Person;
import cn.fchen.netty.thrift.PersonServcie;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @Classname ThriftClient
 * @Description
 * @Date 2019/6/29 14:07
 * @Author by Fchen
 */
public class ThriftClient {
    public static void main(String[] args) {
        TTransport transport = new TFastFramedTransport(new TSocket("localhost",6969),600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonServcie.Client client = new PersonServcie.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUserName("HHH");
            System.out.println(person.getUsername());
            System.out.println("====================");
            Person person2 = new Person();
            person2.setUsername("666");
            person2.setAge(20);
            person2.setMarried(true);
            client.savePerson(person);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            transport.close();
        }
    }
}
