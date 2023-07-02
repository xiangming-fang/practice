package com.xm.jy.test.map;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.map
 * @ClassName: MapTest
 * @Author: albert.fang
 * @Description: map
 * @Date: 2022/12/28 13:01
 */
public class MapTest {
    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("one","one");
//        map1.put("two","two");
//        map1.put("three","three");
//        map1.put("four","four");
//        map1.put("five","five");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("six","six");
        map2.put("seven","seven");
        map2.put("eight","eight");
        map2.put("nine","nine");
        map2.put("ten","ten");
        map2.put("four","four");
        map2.put("five","five");
        HashSet<String> set1 = new HashSet<>(map1.keySet());
        HashSet<String> set2 = new HashSet<>(map2.keySet());
        set1.addAll(set2);
        set1.forEach(System.out::println);

        LocalDate end = LocalDate.parse("2023-01-20");
        LocalDate start = LocalDate.parse("2020-09-01");
        System.out.println("总相差的天数:" + start.until(end, ChronoUnit.DAYS));
        System.out.println("总相差的年数(精度):" + start.until(end, ChronoUnit.DAYS) / 365.0);
        System.out.println("总相差的年数:" + start.until(end, ChronoUnit.YEARS));
    }
}
