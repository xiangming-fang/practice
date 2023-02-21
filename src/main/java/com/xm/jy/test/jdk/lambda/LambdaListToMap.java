package com.xm.jy.test.jdk.lambda;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: albert.fang
 * @date: 2021/3/3 10:16
 * @description: 将list转成map
 */
public class LambdaListToMap {
    public static void main(String[] args) {
        ArrayList<User> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(new User(i+1,"张"+i,30-i,"新宇路"+50+i+"弄"));
        }
//        Map<Integer, User> userMap = arrayList.stream().collect(Collectors.toMap(a -> a.getId(), a -> a));
        Map<Integer, User> userMap = arrayList.stream().collect(Collectors.toMap(User::getId, a -> a));
        System.out.println(userMap);

        // findFirst 获取流中第一个对象
        // findAny 获取流中任意一个
        // ifPresent（logic） 如果存在，执行logic部分代码
        arrayList.stream().findAny().ifPresent(user -> user.setName("第一个名字"));
        System.out.println(arrayList.get(0));
    }
}
@Data
class User{
    int id;
    String name;
    int age;
    String addr;

    User(int id, String name, int age, String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
}
