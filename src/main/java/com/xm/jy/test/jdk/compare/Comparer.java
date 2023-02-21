package com.xm.jy.test.jdk.compare;

import java.util.Comparator;

/**
 * @author: albert.fang
 * @date: 2020/12/24 13:17
 * @description: 比较类（外部比较器）
 */
public class Comparer implements Comparator<SortByComparator> {
    @Override
    public int compare(SortByComparator o1, SortByComparator o2) {
        if (o1.getSortField1() > o2.getSortField1()) return 1;
        else if (o1.getSortField1() < o2.getSortField1()) return -1;
        else if (o1.getSortField1() == o2.getSortField1()){
            if (o1.getSortField2().compareTo(o2.getSortField2()) > 0) return 1;
            else if (o1.getSortField2().compareTo(o2.getSortField2()) < 0) return -1;
        }
        return 0;
    }
}
