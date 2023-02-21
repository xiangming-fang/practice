package com.xm.jy.test.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @ClassName: ClassTest
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/8 13:59
 */
public class ClassTest {
    // 定义一个私有构造器
    private ClassTest(){

    }
    public int a = 9;
    // 定义一个有参数的构造器
    public ClassTest(String name){
        System.out.println("执行有参数的构造器");
    }
    // 定义一个无参数的info方法
    public void info(){
        System.out.println("执行无参数的info方法");
    }
    // 定义一个有参数的info方法
    public void info(String str){
        System.out.println("执行有参数的info方法，其参数值是"+str);
    }
    // 定义一个测试用的内部类
    class Inner{

    }

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {

        Class<?> clazz1 = Class.forName("com.xm.jy.test.jdk.reflect.ClassTest");

        // 下面代码可以获取ClassTest对应的Class
        Class<?> clazz = ClassTest.class;

        ClassTest classTest = new ClassTest();
        Class<?> clazz3 = classTest.getClass();

        Constructor[] ct = clazz.getConstructors();

        // 获取该Class对象所对应类的全部构造器
        Constructor[] constructors = clazz.getDeclaredConstructors();
        System.out.println("全部构造器——————————————");
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        // 获取该Class对象所对应类的全部public构造器
        Constructor[] ctor = clazz.getConstructors();
        System.out.println("public构造器————————————");
        for (Constructor constructor : ctor) {
            System.out.println(constructor);
        }
        // 获取该Class对象所对应的全部public方法
        Method[] methods = clazz.getMethods();
        System.out.println("所有public方法");
        for (Method method : methods) {
            System.out.println(method);
        }
        // 获取该Class对象所对应类的指定方法
        System.out.println("ClassTest里带有一个字符串参数的info方法："+clazz.getMethod("info",String.class));
        // 获取该Class对象所对应类的全部内部类
        Class<?>[] inners = clazz.getDeclaredClasses();
        System.out.println("ClassTest的全部内部类如下:");
        for (Class<?> inner : inners) {
            System.out.println(inner);
        }
        // 使用Class.forName()方法加载ClassTest的Inner内部类
//        Class inClazz = Class.forName("ClassTest$Inner");
        // 通过getDeclaringClass()访问该类所在的外部类
//        System.out.println("inClazz对应的外部类为："+inClazz.getDeclaringClass());
        System.out.println("ClassTest的包为："+clazz.getPackage());
        System.out.println("ClassTest的父类为:"+clazz.getSuperclass());
    }

}
