package com.xm.jy.test.jdk.lambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: albert.fang
 * @date: 2021/4/29 10:54
 * @description: 集合中找到最大的元素
 */
public class Max {


    @Test
    // v1.compareTo(v2) > 0 说明v1比v2大
    // v1.compareTo(v2) < 0 说明v1比v2小
    // v1.compareTo(v2) = 0 说明v1和v2相等

    // 顺序的的说法
    // 如果在v1比v2大的前提下，返回正数表示正序（小 -> 大），返回负数表示倒序 （大 -> 小）
    void testSimple(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(27);
        list.add(24);
        list.add(25);
        // 升序排序
        List<Integer> asc = list.stream().sorted((v1, v2) -> v1.compareTo(v2)).collect(Collectors.toList());
        List<Integer> desc = list.stream().sorted((v1, v2) -> {
            if (v1.compareTo(v2) > 0) {
                return -1;
            } else if (v1.compareTo(v2) < 0) {
                return 1;
            }
            return 0;
        }).collect(Collectors.toList());
        // 按升序排序得到最大值
        int maxInteger = list.stream().max((v1, v2) -> v1.compareTo(v2)).get();
        // 按降序排序得到最大值
        int maxInter = list.stream().max((v1, v2) -> {
            if (v1.compareTo(v2) > 0) {
                return -1;
            } else if (v1.compareTo(v2) < 0) {
                return 1;
            }
            return 0;
        }).get();
        System.out.println();
    }
}
