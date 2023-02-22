package com.xm.jy.test.jdk.reflect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

// 从 Java 7 开始，除了反射之外，在 java.lang.invoke 包中新增了 MethodHandle 这个类，
// 它的基本功能与反射中的 Method 类似，但它比反射更加灵活。
// 反射是 Java API 层面支持的一种机制，MethodHandle 则是 JVM 层支持的机制，相较而言，反射更加重量级，
// MethodHandle 则更轻量级，性能也比反射更好些。
public class MethodHandleDemo {

    // 定义一个sayHello()方法
    public String sayHello(String s) {
        return "Hello, " + s;
    }

//    使用 MethodHandle 进行方法调用的时候，往往会涉及下面几个核心步骤：
//
//    1、创建 MethodType 对象，确定方法的签名，这个签名会涉及方法参数及返回值的类型；
//
//    2、在 MethodHandles.Lookup 这个工厂对象中，根据方法名称以及上面创建的 MethodType 查找对应 MethodHandle 对象；
//
//    3、将 MethodHandle 绑定到一个具体的实例对象；
//
//    4、调用 MethodHandle.invoke()/invokeWithArguments()/invokeExact() 方法，完成方法调用。

    public static void main(String[] args) throws Throwable {
        // 初始化MethodHandleDemo实例
        MethodHandleDemo subMethodHandleDemo = new SubMethodHandleDemo();
        // 定义sayHello()方法的签名，第一个参数是方法的返回值类型，第二个参数是方法的参数列表
        MethodType methodType = MethodType.methodType(String.class, String.class);
        // 根据方法名和MethodType在MethodHandleDemo中查找对应的MethodHandle
        MethodHandle methodHandle = MethodHandles.lookup()
                .findVirtual(MethodHandleDemo.class, "sayHello", methodType);
        // 将MethodHandle绑定到一个对象上，然后通过invokeWithArguments()方法传入实参并执行
        System.out.println(methodHandle.bindTo(subMethodHandleDemo)
                .invokeWithArguments("MethodHandleDemo"));
        // 下面是调用MethodHandleDemo对象(即父类)的方法
        MethodHandleDemo methodHandleDemo = new MethodHandleDemo();
        System.out.println(methodHandle.bindTo(methodHandleDemo)
                .invokeWithArguments("MethodHandleDemo"));
    }
    public static class SubMethodHandleDemo extends MethodHandleDemo{
        // 定义一个sayHello()方法
        public String sayHello(String s) {
            return "Sub Hello, " + s;
        }
    }


}
