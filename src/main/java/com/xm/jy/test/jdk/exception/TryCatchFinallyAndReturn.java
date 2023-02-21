package com.xm.jy.test.jdk.exception;

/**
 * @author: albert.fang
 * @date: 2021/3/11 15:23
 * @description: try catch finally return执行流程
 */
public class TryCatchFinallyAndReturn {

    public static void main(String[] args) {
        test2();
    }

    public static String test2(){
        try {
            return test1();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("try代码块里的return，等会执行，容我先打印个东西");
        }
        return null;
    }

    private static String test1(){
        System.out.println("我想被return");
        return "我想给你返回点东西";
    }
}
