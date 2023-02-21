package com.xm.jy.test.jdk.annotation;

import java.lang.reflect.Method;

/**
 * @author: albert.fang
 * @date: 2021/1/7 10:28
 * @description: 使用自定义注解
 */
public class UseDefinationAnnotation {
    @Definition(name = "方翔鸣",age = 24)
    public void get(){
        System.out.println("这个注解起了什么用？？？");
    }

    public void getAnnotation(){
        System.out.println("annotation测试");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class<UseDefinationAnnotation> clazz = UseDefinationAnnotation.class;
        UseDefinationAnnotation useDefinationAnnotation = clazz.newInstance();
        useDefinationAnnotation.get();
        // 如何获取方法上使用过的注解相应属性
        Method method = clazz.getMethod("getAnnotation");
        if (method.isAnnotationPresent(Definition.class)) {
            System.out.println("方法：" + method.getName()+"上有Definition注解");
            // 获取该方法上的Definition注解
            Definition annotation = method.getAnnotation(Definition.class);
            System.out.println(method.getName()+"方法上Definition注解的属性有：");
            System.out.println(annotation.name());
            System.out.println(annotation.age());
            System.out.println(annotation.address());
        }else {
            System.err.println("方法：" + method.getName()+"上没有有Definition注解");
        }
    }
}
