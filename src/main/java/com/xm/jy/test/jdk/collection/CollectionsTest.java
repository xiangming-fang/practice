package com.xm.jy.test.jdk.collection;

import com.xm.jy.job_cx.model.College;
import org.springframework.beans.BeanUtils;

import java.util.*;

/**
 * @author: albert.fang
 * @date: 2020/9/7 14:33
 * @description: 集合工具类Collections的应用测试
 */
public class CollectionsTest {
    public static void main(String[] args) {
        // 建立一个southCollege对象往里面填充3个计算机学院和4个文学院的学院对象
        List<College> southCollege = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            College college = new College();
            college.setCollegeName("计算机学院");
            college.setCountNum(12);
            college.setLevel(1);
            southCollege.add(college);
        }
        for (int i = 0; i < 4; i++) {
            College college = new College();
            college.setCollegeName("文学院");
            college.setCountNum(66);
            college.setLevel(2);
            southCollege.add(college);
        }
        // 建立一个northCollege对象往里面填充3个建筑材料学院和6个食品学院的学院对象
        List<College> northCollege = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            College college = new College();
            college.setCollegeName("建筑材料学院");
            college.setCountNum(52);
            college.setLevel(3);
            northCollege.add(college);
        }
        for (int i = 0; i < 6; i++) {
            College college = new College();
            college.setCollegeName("食品学院");
            college.setCountNum(88);
            college.setLevel(4);
            northCollege.add(college);
        }

        southCollege.addAll(northCollege);
        System.out.println(southCollege.size());
        System.out.println(northCollege.size());
        List<College> colleges = mergeSameCollegeName(southCollege).subList(0,3);
        System.out.println("最终结果~~~~~~~~~");
        // 最终结果
        colleges.forEach(v -> {
            System.out.println(v.toString());
        });
        System.out.println("git测试");
    }
    // 将List中相同学院名称的到馆次数合并
    public static List<College> mergeSameCollegeName(List<College> collegeList){
        Map<String,College> map = new HashMap<>();
        collegeList.forEach( v -> {
            if (!map.containsKey(v.getCollegeName())){
                map.put(v.getCollegeName(),v);
            }
            else {
                College college = new College();
                BeanUtils.copyProperties(v,college);
                college.setCountNum(map.get(v.getCollegeName()).getCountNum() + v.getCountNum());
                map.put(v.getCollegeName(),college);
            }
        });
        List<College> result = new ArrayList<>();
        map.forEach( (k,v) -> result.add(v));
        Collections.sort(result);
        return result;
    }
}
