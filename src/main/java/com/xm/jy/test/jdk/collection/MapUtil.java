package com.xm.jy.test.jdk.collection;

import java.util.*;

/**
 * @auther 方翔鸣
 * @date 2020/3/9 20:24
 */
public class MapUtil {
    public static Map<Integer,String> map = Collections.synchronizedMap(new HashMap<>());
    private static List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());
//    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
    static {
        map.put(1,"方翔鸣");
        map.put(2,"林就远");
        map.put(3,"于欣欣");
        map.put(4,"张宇");
        map.put(5,"曾小贤");
        map.put(6,"胡一菲");
    }
}
