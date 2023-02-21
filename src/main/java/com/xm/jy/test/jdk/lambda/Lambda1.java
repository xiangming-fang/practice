package com.xm.jy.test.jdk.lambda;

/**
 * @ClassName: Lambda1
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/9 9:17
 */
public class Lambda1 {
    @FunctionalInterface
    interface One{
        void test1();
    }

    interface Two{
        void test2(int a);
    }

    interface Three{
        int test3(int b);
    }

    public static void main(String[] args) {
        One one = () -> System.out.println("无参数，无返回值");
        one.test1();
        Two two = (v) -> System.out.println("有参数，参数为："+v+";无返回值");
        two.test2(520);
        Three three = (z) -> z+1;
        System.out.println(three.test3(519));
    }
}
