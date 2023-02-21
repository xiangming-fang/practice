package com.xm.jy.test.jdk.interfaces;

/**
 * @ClassName: C
 * @Description: 类的作用描述
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/27 14:05
 */
public class C {
    public static void getA(A a){
        if (a instanceof B){
            System.out.println("该参数的类型是实现了A接口的B类");
        }else if (a instanceof D){
            System.out.println("该参数的类型是实现了A接口的D类");
        }else if (a instanceof E){
            System.out.println("该参数的类型是实现了E接口的F类");
        }

    }

    public static void main(String[] args) {
        B b = new B();
        getA(new D());
        getA(b);
        getA(new F());
    }
}
