package com.xm.jy.test.jdk.io;

import org.junit.jupiter.api.Test;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.io
 * @ClassName: SimpleTest
 * @Author: albert.fang
 * @Description: 简单测试
 * @Date: 2021/7/1 10:14
 */
public class SimpleTest {

    private static final String SEPARATOR = ";";


    @Test
    void testBootStrapClassLoader(){
        String bootStrapClassLoadPath = System.getProperty("sun.boot.class.path");
        for (String singlePath : bootStrapClassLoadPath.split(SEPARATOR)) {
            System.out.println(singlePath);
        }

    }

    @Test
    void testExtensionClassLoader(){
        String extensionClassLoaderPath = System.getProperty("java.ext.dirs");
        for (String singlePath : extensionClassLoaderPath.split(SEPARATOR)) {
            System.out.println(singlePath);
        }
    }

    @Test
    void testSystemClassLoader(){
        String systemClassLoaderPath = System.getProperty("java.class.path");
        for (String singlePath : systemClassLoaderPath.split(SEPARATOR)) {
            System.out.println(singlePath);
        }
    }

    @Test
    void testClassLoader(){
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }
}
