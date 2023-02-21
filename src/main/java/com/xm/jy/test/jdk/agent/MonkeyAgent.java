package com.xm.jy.test.jdk.agent;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.agent
 * @ClassName: MonkeyAgent
 * @Author: albert.fang
 * @Description: 猴子的静态代理
 * @Date: 2023/2/20 21:29
 */
public class MonkeyAgent implements Animal{

    private Monkey monkey;

    public MonkeyAgent(){
        monkey = new Monkey();
    }

    @Override
    public String eat() {
        return monkey.eat();
    }
}
