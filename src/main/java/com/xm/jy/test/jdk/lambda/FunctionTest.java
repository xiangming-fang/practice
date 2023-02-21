package com.xm.jy.test.jdk.lambda;

import java.util.HashMap;

/**
 * @Author: albert.fang
 * @Description: Function(T ， R)
 * @Date: 2023/2/16 16:41
 */
public class FunctionTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("id","1");
        // 存在不插入
        map.putIfAbsent("id", "2");
        // 不存在则插入
        map.putIfAbsent("name","xiangming.fang");
        System.out.println(map.toString());
        map.computeIfAbsent("sex", t -> t + "-man");
        System.out.println(map.toString());
    }
}
