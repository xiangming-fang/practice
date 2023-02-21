package com.xm.jy.test.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 17:55
 * 创建Class对象，并对Class对象的相关操作：属性、方法、构造方法。
 * 通过Class对象创建相关类的对象。
 */
public class ReflectTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 首先得到Class对象
        Class clazz1 = ReflectEntity.class;
        Class clazz2 = Class.forName("com.xm.jy.test.jdk.reflect.ReflectEntity");
        // 通过Class对象 对 属性的操作
        // 得到所有属性
        for (Field declaredField : clazz1.getDeclaredFields()) {
            System.out.println(declaredField);
        }
        // 得到属性域为public的方法
        for (Field declaredField : clazz2.getFields()) {
            System.out.println(declaredField);
        }
        // 通过Class对象 对 方法的操作
        // 得到本类中的所有方法，不包括构造方法
        System.out.println("all method start");
        for (Method declaredMethod : clazz1.getDeclaredMethods()) {
            System.out.println(declaredMethod);
        }
        System.out.println("public method start");
        // 得到方法域为 public 的方法，包括父类，不包括构造方法
        for (Method method : clazz2.getMethods()) {
            System.out.println(method);
        }
        System.out.println("all constructor method start");
        // 对构造方法的操作 得到所有
        for (Constructor declaredConstructor : clazz1.getDeclaredConstructors()) {
            System.out.println(declaredConstructor);
        }
        System.out.println("public constructor method start");
        // 得到构造方法的域为public 的构造方法
        for (Constructor constructor : clazz2.getConstructors()) {
            System.out.println(constructor);
        }
        // 通过Class对象创建相关类对象
        // 第一种方法直接clazz.newInstance()创建相关类对象，调用的是无参构造；若无参构造是私有的，则该种方法不能成功创建相关类对象
//        ReflectEntity reflectEntity = (ReflectEntity)clazz1.newInstance();
        // 第二种方法：得到具体的constructor方法，传参进去进去创建对象
        Constructor constructor = clazz1.getConstructor(String.class,
                Integer.class);
        ReflectEntity reflectEntity1 =
                (ReflectEntity)constructor.newInstance("方翔鸣",24);
        reflectEntity1.setGender("男");
        System.out.println(reflectEntity1.toString());
    }
}
