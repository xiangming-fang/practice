package com.xm.jy.test.jdk.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: albert.fang
 * @date: 2021/6/21 11:05
 * @description: findany && ifpresent
 */
public class FindAny {
    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(2L);
        list.add(3L);
        list.stream().findAny().ifPresent(v -> System.out.println("有值" + v));
        list.stream().findAny().ifPresent(v -> System.out.println("有值"));
    }
}
