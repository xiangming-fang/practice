package com.xm.jy.test.jdk.reflect.clazz;

/**
 * @Author: albert.fang
 * @Description:
 * @Date: 2023/2/16 17:49
 */
public class AssignedTest {
    public static void main(String[] args) {
        String world = "hello world";
        // 父.isAssignableFrom(子) true
        System.out.println(String.class.isAssignableFrom(Object.class));
        System.out.println(Object.class.isAssignableFrom(String.class));
    }
}
