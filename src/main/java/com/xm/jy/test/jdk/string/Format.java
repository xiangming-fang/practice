package com.xm.jy.test.jdk.string;

import java.util.Date;

/**
 * @author: albert.fang
 * @date: 2021/3/12 13:52
 * @description: String的format，String的format函数和System.out.printf函数的占位符是一样的。底层调用的都是同一个方法：Formatter:format 2493
 */
public class Format {
    public static void main(String[] args) {
        test1();
    }

    private static void test1(){
        System.out.println(String.format("%tc",new Date()));
    }
}
