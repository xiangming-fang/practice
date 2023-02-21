package com.xm.jy.test.jdk.collection;

import java.util.Scanner;

/**
 * @auther 方翔鸣
 * @date 2020/3/7 12:41
 * Map集合的遍历
 */
public class MapTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            String sc = input.next();
            for (Integer integer : MapUtil.map.keySet()) {
                if (sc.equals(integer.toString())){
                    System.out.println(MapUtil.map.get(integer));
                }
            }
            if (sc.equals("exit")){
                break;
            }
        }
/*        Integer integer = new Integer(6);
        Integer integer1 = 6;
        int anInt = 6;
        System.out.println(integer == integer1); // 两个都是包装类型，那么就要比较hashcode()，所以不相等。
        System.out.println(integer == anInt);
        System.out.println(integer1 == anInt);*/
    }
}
