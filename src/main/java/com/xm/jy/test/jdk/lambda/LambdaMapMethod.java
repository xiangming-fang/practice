package com.xm.jy.test.jdk.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: albert.fang
 * @date: 2021/2/26 16:06
 * @description: lambda中的map方法用法
 */
public class LambdaMapMethod {
    public static void main(String[] args) {
        // List
        List<String> telephones = new ArrayList<>();
        telephones.add("12333126601x");
        telephones.add(" 1233312660s1414");
        telephones.add(" 1233312660141423");
        List<String> finP = telephones.stream().map(String::trim).distinct().collect(Collectors.toList());
        System.out.println();
        // Map
        // map是对集合中每个元素进行操作之后返回新的流
        // filter直接从当前流中查找符合条件，不符合的剔除
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>(16);
        hashMap.put("aaa","aaabbbccc");
        hashMap.put("bbb","aaabbbcc");
        hashMap.put("ccc","bbb");
        List<Boolean> cc = hashMap.entrySet().stream().map(entry -> entry.getValue().contains("bbcc")).collect(Collectors.toList());
        List<Map.Entry<String, String>> bbb = hashMap.entrySet().stream().filter(entry -> entry.getValue().contains("cc")).collect(Collectors.toList());
        System.out.println();

    }
}
