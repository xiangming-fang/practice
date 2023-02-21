package com.xm.jy.test.jdk.reflect.clazz;

/**
 * @Author: albert.fang
 * @Description:
 * @Date: 2023/2/17 14:35
 */
public class SimpleNameTest {
    public static void main(String[] args) {
        // 不是类的全路径名
        // 输出 SimpleNameTest
        System.out.println(SimpleNameTest.class.getSimpleName());
    }
}
