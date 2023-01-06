package com.xm.jy.test.math;

/**
 * @Author: albert.fang
 * @Description: 对数
 * @Date: 2023/1/5 15:17
 */
public class Log {
    public static void main(String[] args) {
        // 求 log 以 2 为 底 100 万的对数
        double v = Math.log(1e7) / Math.log(2);
        System.out.println(v);
    }
}
