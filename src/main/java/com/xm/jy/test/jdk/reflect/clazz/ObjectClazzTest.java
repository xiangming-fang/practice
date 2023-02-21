package com.xm.jy.test.jdk.reflect.clazz;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.clazz
 * @ClassName: ObjectClazzTest
 * @Author: albert.fang
 * @Description:
 * @Date: 2023/2/16 21:58
 */
public class ObjectClazzTest {
    public static void main(String[] args) {
        // Object 的父类是 null，并不是 Object 本身
        System.out.println(Object.class.getSuperclass());
    }
}
