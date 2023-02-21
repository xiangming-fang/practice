package com.xm.jy.test.jdk.compare;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * @author: albert.fang
 * @date: 2020/12/17 17:12
 * @description: 利用比较器排序
 */
public class CombinationProductSort {
    private static ArrayList<JSONObject> pendingList;

    static {
        pendingList = new ArrayList<>();
        pendingList.add(new JSONObject().fluentPut("id","m_2512").fluentPut("sort",2512).fluentPut("type",3));
        pendingList.add(new JSONObject().fluentPut("id","m_2447").fluentPut("sort",2447).fluentPut("type",3));
        pendingList.add(new JSONObject().fluentPut("id","m_2378").fluentPut("sort",2378).fluentPut("type",3));
        pendingList.add(new JSONObject().fluentPut("id","s_25").fluentPut("sort",6).fluentPut("type",2));
        pendingList.add(new JSONObject().fluentPut("id","s_41").fluentPut("sort",5).fluentPut("type",2));
        pendingList.add(new JSONObject().fluentPut("id","s_36").fluentPut("sort",4).fluentPut("type",2));
        pendingList.add(new JSONObject().fluentPut("id","s_356").fluentPut("sort",4).fluentPut("type",2));
        pendingList.add(new JSONObject().fluentPut("id","s_50").fluentPut("sort",3).fluentPut("type",2));
        pendingList.add(new JSONObject().fluentPut("id","t_78").fluentPut("sort",8).fluentPut("type",1));
        pendingList.add(new JSONObject().fluentPut("id","t_77").fluentPut("sort",7).fluentPut("type",1));
        pendingList.add(new JSONObject().fluentPut("id","t_76").fluentPut("sort",6).fluentPut("type",1));
        pendingList.add(new JSONObject().fluentPut("id","t_75").fluentPut("sort",5).fluentPut("type",1));
        pendingList.add(new JSONObject().fluentPut("id","t_74").fluentPut("sort",4).fluentPut("type",1));
        pendingList.add(new JSONObject().fluentPut("id","t_73").fluentPut("sort",3).fluentPut("type",1));
        pendingList.add(new JSONObject().fluentPut("id","t_72").fluentPut("sort",3).fluentPut("type",1));
    }

    public static void main(String[] args) {

        pendingList.sort(((o1, o2) -> {
            Integer sort1 = o1.getObject("sort", Integer.class);
            Integer sort2 = o2.getObject("sort", Integer.class);
            if (sort1 > sort2) return 1;
            else if (sort1 < sort2) return -1;
                // 相等的情况就有很多种了。
                // 1、同为工具： 用产品id升序
                // 2、同为方案： 用产品id升序
                // 3、一个工具，一个方案： 工具优先
            else {
                String id1 = o1.getObject("id", String.class);
                String id2= o2.getObject("id", String.class);

                long productId1 = Long.parseLong(id1.split("_")[1]);
                long productId2 = Long.parseLong(id2.split("_")[1]);
                Integer type1 = o1.getObject("type", Integer.class);
                Integer type2 = o2.getObject("type", Integer.class);
                // 1、同为工具
                if (id1.contains("t") && id2.contains("t")){
                    if (productId1 > productId2) return 1;
                    else if (productId1 < productId2) return -1;
                }
                // 2、同为方案
                else if (id1.contains("s") && id2.contains("s")){
                    if (productId1 > productId2) return 1;
                    else if (productId1 < productId2) return -1;
                }
                // 3、一个工具，一个方案（1）
                else if (id1.contains("t") && id2.contains("s")){
                    if (type1 > type2) return 1;
                    else if (type1 < type2) return -1;
                }
                // 3、一个方案，一个工具（2）
                else if (id1.contains("s") && id2.contains("t")){
                    if (type1 > type2) return 1;
                    else if (type1 < type2) return -1;
                }
            }
            return 0;
        }));

        for (JSONObject object : pendingList) {
            System.out.println(object.toJSONString());
        }


    }

}
