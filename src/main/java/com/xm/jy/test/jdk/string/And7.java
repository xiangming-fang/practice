package com.xm.jy.test.jdk.string;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.jdk.string
 * @ClassName: And7
 * @Author: albert.fang
 * @Description: &7
 * @Date: 2023/3/7 20:13
 */
public class And7 {

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println((i & 8) > 8 ? i : (i & 7));
        }
    }
}
