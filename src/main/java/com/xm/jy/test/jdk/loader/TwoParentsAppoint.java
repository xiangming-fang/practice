package com.xm.jy.test.jdk.loader;


/**
 * @ProjectName: practice
 * @Package: com.xm.jy.loader
 * @ClassName: TwoParentsAppoint
 * @Author: albert.fang
 * @Description: 双亲委派机制测试
 * @Date: 2021/7/1 14:17
 */
public class TwoParentsAppoint {
    public static void main(String[] args) {
        try {
            // 查看当前系统 类路径中包含的路径条目
            System.out.println(System.getProperty("java.class.path"));
            // 调用当前类的类加载器（这里指系统类加载器）来加载User
            Class<?> clazz = Class.forName("com.xm.jy.job_cx.model.User");
            // 查看User 是被哪个类加载器加载的
            System.out.println(clazz.getClassLoader());
        } catch (ClassNotFoundException e) {
            //
        }

    }
}
