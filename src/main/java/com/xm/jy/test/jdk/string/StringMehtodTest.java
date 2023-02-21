package com.xm.jy.test.jdk.string;

/**
 * @auther 方翔鸣
 * @date 2020/3/7 15:01
 */
public class StringMehtodTest {
    public static void main(String[] args) {
        String s = "wo,jin,tian,zai,gan,ma";
        // split方法
        for (String s1 : s.split(",")) {
            System.out.println(s1);
        }
        String tmp = "1";
        System.out.println(tmp.substring(1));
    }
}
