package cn.fchen.netty.thrift.example;

import cn.fchen.netty.thrift.DataException;
import cn.fchen.netty.thrift.Person;
import cn.fchen.netty.thrift.PersonServcie;
import org.apache.thrift.TException;

/**
 * @Classname PersonServiceImpl
 * @Description 实现类
 * @Date 2019/6/29 13:56
 * @Author by Fchen
 */
public class PersonServiceImpl implements PersonServcie.Iface {
    @Override
    public Person getPersonByUserName(String username) throws DataException, TException {
        System.out.println("getPersonByUserName client Param："+ username);
        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson client params: ");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
    }
}
