package com.xm.jy.test.jdk.bit;

/**
 * @Author: xiangming.fang
 * @Date: 2023/11/8 15:30
 */
public class YiHuo {

    /**
     * 两次异或操作
     * @param args
     */
    public static void main(String[] args) {
        int sign = 1;
        int flag = 0;
        while ( flag < 32 ){
            int random = 55456;
            int pow = sign << flag++;
            System.out.println("原sku：" + random);
            System.out.println("打标之后：" + (random ^ pow));
            System.out.println("去标之后:" + (random ^ pow ^ pow));
            System.out.println("****************************");
        }
    }
}
