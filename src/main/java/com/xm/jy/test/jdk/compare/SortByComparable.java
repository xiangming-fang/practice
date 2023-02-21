package com.xm.jy.test.jdk.compare;

import lombok.Data;

/**
 * @author: albert.fang
 * @date: 2020/12/23 18:25
 * @description: 通过Comparable排序
 */
@Data
public class SortByComparable implements Comparable<SortByComparable> {

    private int sortField1;

    private String sortField2;

    @Override
    public int compareTo(SortByComparable o) {
        if (this.getSortField1() > o.getSortField1()) return 1;
        else if (this.getSortField1() < o.getSortField1()) return -1;
        else if (this.getSortField1() == o.getSortField1()){
            if (this.getSortField2().compareTo(o.getSortField2()) > 0) return 1;
            else if (this.getSortField2().compareTo(o.getSortField2()) < 0) return -1;
        }
        return 0;
    }
}
