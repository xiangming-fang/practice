package com.xm.jy.test.jdk.lambda;

import java.util.*;

/**
 * @author: albert.fang
 * @date: 2020/8/10 10:08
 * @description: forEach遍历list、set、map集合
 */
public class Lambda2 {
    public static void main(String[] args) {
        List<Lambda2Entity> list = new ArrayList<>();
        Set<Lambda2Entity> set = new LinkedHashSet<>();
        Map<String,Lambda2Entity> map = new LinkedHashMap<>();
        for (int i = 1; i <= 6; i++) {
            Lambda2Entity entity = new Lambda2Entity();
            entity.setAge(i+10);
            entity.setName("张三"+i+"号");
            list.add(entity);
            set.add(entity);
            map.put(entity.getName(),entity);
        }

        // 用forEach遍历list集合
        System.out.println("用forEach遍历list集合");
        list.forEach((single) -> {
            System.out.println(single.getName() + "  " + single.getAge());
        });

        System.out.println("用forEach遍历set集合");
        set.forEach((single) -> {
            System.out.println(single.getName() + "  " + single.getAge());
        });

        System.out.println("用forEach遍历map集合");
        map.forEach((key,value) -> {
            System.out.println(key + " " + value.getAge());
        });
    }
}
class Lambda2Entity{
    private String name;
    private Integer age;

    public Lambda2Entity(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Lambda2Entity() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
