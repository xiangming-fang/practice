package com.xm.jy.test.jdk.agent;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.agent
 * @ClassName: MonkeyAgentTest
 * @Author: albert.fang
 * @Description: 猴子动物的代理
 * @Date: 2023/2/20 21:31
 */
public class MonkeyAgentTest {
    public static void main(String[] args) throws ClassNotFoundException {
        MonkeyAgent monkeyAgent = new MonkeyAgent();
        System.out.println(monkeyAgent.eat());
    }
}
