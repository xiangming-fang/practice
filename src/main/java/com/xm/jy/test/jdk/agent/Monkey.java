package com.xm.jy.test.jdk.agent;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.agent
 * @ClassName: Monkey
 * @Author: albert.fang
 * @Description: 猴子 - 具体的动物
 * @Date: 2023/2/20 21:28
 */
public class Monkey implements Animal{
    @Override
    public String eat() {
        return "猴子吃香蕉、水蜜桃";
    }
}
