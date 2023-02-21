package com.xm.jy.test.jdk.lambda;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.lambda
 * @ClassName: SetTest
 * @Author: albert.fang
 * @Description:
 * @Date: 2023/2/16 20:17
 */
public class SetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("hello");
        set.add("world");
        String[] setArray = set.toArray(new String[0]);
        System.out.println(setArray.length);
        System.out.println(Arrays.stream(setArray).collect(Collectors.joining()).toString());
    }
}
