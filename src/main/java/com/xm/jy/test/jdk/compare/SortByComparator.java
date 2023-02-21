package com.xm.jy.test.jdk.compare;

import lombok.Data;

import java.util.Comparator;

/**
 * @author: albert.fang
 * @date: 2020/12/24 10:09
 * @description: 通过comparator排序
 */
@Data
public class SortByComparator implements Comparator<SortByComparator>{

    private int sortField1;

    private String sortField2;

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
