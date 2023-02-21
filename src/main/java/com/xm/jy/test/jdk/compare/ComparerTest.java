package com.xm.jy.test.jdk.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: albert.fang
 * @date: 2020/12/23 18:40
 * @description: 比较器测试
 */
public class ComparerTest {
    private static List<SortByComparable> comparables = new ArrayList<>();
    private static List<SortByComparator> comparators = new ArrayList<>();
    static {
        for (int i = 0; i < 10; i++) {
            SortByComparable sortByComparable = new SortByComparable();
            if ( i==3 || i==9 || i==6){
                sortByComparable.setSortField1(3);
            }else {
                sortByComparable.setSortField1(i);
            }
            sortByComparable.setSortField2("第"+i+"排序");
            comparables.add(sortByComparable);
        }

        for (int i = 0; i < 10; i++) {
            SortByComparator sortByComparator = new SortByComparator();
            if ( i==3 || i==9 || i==6){
                sortByComparator.setSortField1(3);
            }else {
                sortByComparator.setSortField1(i);
            }
            sortByComparator.setSortField2("第"+i+"排序");
            comparators.add(sortByComparator);
        }



    }
    public static void main(String[] args) {
        // comparable 内部比较器
        Collections.sort(comparables);
        for (SortByComparable comparable : comparables) {
            System.out.println(comparable.toString());
        }
        System.out.println("**********************");
        // comparator 外部比较器1（直接在lambda表达式表示，个人喜欢这种）
//        Collections.sort(comparators,(o1, o2) -> {
//            if (o1.getSortField1() > o2.getSortField1()) return 1;
//            else if (o1.getSortField1() < o2.getSortField1()) return -1;
//            else if (o1.getSortField1() == o2.getSortField1()){
//                if (o1.getSortField2().compareTo(o2.getSortField2()) > 0) return 1;
//                else if (o1.getSortField2().compareTo(o2.getSortField2()) < 0) return -1;
//            }
//            return 0;
//        });
        // comparator 外部比较器2（封装一个比较器类）
        Collections.sort(comparators,new Comparer());
        for (SortByComparator comparator : comparators) {
            System.out.println(comparator.toString());
        }


    }
}
