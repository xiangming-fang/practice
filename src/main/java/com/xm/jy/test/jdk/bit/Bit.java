package com.xm.jy.test.jdk.bit;

import java.util.Random;

/**
 * @Author: xiangming.fang
 * @Description: 位
 * @Date: 2023/3/6 9:48
 */
public class Bit {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString('h'));
        System.out.println((int)'h');
        System.out.println(Integer.toBinaryString('e'));
        System.out.println((int)'e');
        System.out.println(Integer.toBinaryString('l'));
        System.out.println((int)'l');
        int n = 100000;
        Random random = new Random();
        while (n -- > 0){
            // a 必须是非负数
            int a = random.nextInt(100000);
            int c = random.nextInt(20);
            // 这里b必须是2的幂
            int b = (int)Math.pow(2, c);
            if (a%b != (a & (b-1))){
                System.out.printf("n = %d\n",n);
                System.out.printf("%d  %d\n",a,b);
                System.out.printf("a%%b = %d\n",a%b);
                System.out.printf("a&b = %s\n",a&(b-1));
                System.out.println("怎么出现不相等了?");
                throw new RuntimeException("不符合预期");
            }
        }
        System.out.println("是我想要的结果");
    }
}

