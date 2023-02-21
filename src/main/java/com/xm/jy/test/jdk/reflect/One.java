package com.xm.jy.test.jdk.reflect;

import java.lang.reflect.Constructor;

/**
 * @ClassName: OneClass
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/8 9:31
 */
public class One {
    private static final int a = 0;

    public static void main(String[] args) throws Exception {
        Class<?> clazz = ClassTest.class;

        // 使用反射创建对象的第一种方式
        ClassTest classTest1 = (ClassTest) clazz.newInstance();
        System.out.println(classTest1.a);

        // 使用反射创建对象的第二种方式
        Constructor constructor = clazz.getConstructor(String.class);
        ClassTest classTest = (ClassTest) constructor.newInstance("hello");
        System.out.println(classTest.a);
        classTest.info("test");
    }
}
