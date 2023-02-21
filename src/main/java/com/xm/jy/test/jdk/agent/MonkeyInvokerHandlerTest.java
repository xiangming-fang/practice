package com.xm.jy.test.jdk.agent;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.agent
 * @ClassName: MonkeyInvokerHandlerTest
 * @Author: albert.fang
 * @Description: 猴子动态代理测试类
 * @Date: 2023/2/20 21:50
 */
public class MonkeyInvokerHandlerTest {
    public static void main(String[] args) {
        Animal animal = new Monkey();
        MonkeyInvokerHandler invokerHandler = new MonkeyInvokerHandler(animal);
        Animal proxyObject = (Animal)invokerHandler.getProxyObject();
        proxyObject.eat();
    }
}
