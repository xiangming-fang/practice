package com.xm.jy.test.jdk.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: albert.fang
 * @date: 2020/8/10 10:23
 * @description: lambda中的Stream操作
 */
public class Lambda3 {
    public static void main(String[] args) {
        List<Lambda2Entity> list = new ArrayList<>();
        Set<Lambda2Entity> set_goalbage = new HashSet<>();
        Set<String> set;
        Map<String,Integer> map;
        for (int i = 1; i <= 6; i++) {
            Lambda2Entity entity = new Lambda2Entity();
            entity.setAge(i+10);
            entity.setName("张三"+i+"号");
            list.add(entity);
            set_goalbage.add(entity);
        }
        // 将list中对象的name属性提取出去，通过流的形式封装成一个单独的list对象
        List<String> list1 = list.stream().map(Lambda2Entity::getName).collect(Collectors.toList());
        list1.forEach(System.out::println);
//        list1.forEach(s -> System.out.println(s));

        // 将list转成set
        System.out.println("将list转成set");
        set = list.stream().map(Lambda2Entity::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        // 将list转成map
        System.out.println("将list转成map");
        map = list.stream().collect(Collectors.toMap(Lambda2Entity::getName,Lambda2Entity::getAge));
        map.forEach((k,v) -> {
            System.out.println(k + "  " + v);
        });

        // 将map转成list
        System.out.println("将map转成list");
//        List<String> list2 = new ArrayList<>(map.keySet());
        List<String> list2 = map.entrySet().stream().map(single -> single.getKey()).collect(Collectors.toList());
        list2.forEach(System.out::println);

        // 将map转成set
        System.out.println("将map转成set");
        Set<String> set2 = map.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toSet());
        set2.forEach(System.out::println);

        // set转成list
        System.out.println("set转成list");
        List<String> setToList = set.stream().collect(Collectors.toList());
        setToList.forEach(System.out::println);

        // set转成map
        System.out.println("set转成map");
        Map<String, Lambda2Entity> hashMap = set_goalbage.stream().collect(Collectors.toMap(Lambda2Entity::getName, v -> new Lambda2Entity(v.getName(),v.getAge())));
        hashMap.forEach((k,v) -> {
            System.out.println(k+" " + v.getAge());
        });
    }
}
