package com.xm.jy.test.jdk.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.decimal
 * @ClassName: BigDecimalTest
 * @Author: albert.fang
 * @Description:
 * @Date: 2022/11/3 15:09
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal diveHours = new BigDecimal(2 * 6.5).subtract(new BigDecimal(3.0));
        System.out.println(diveHours);
        // RoundingMode.HALF_UP：正数向上整数、负数向下取整数
        // scale：保留几位小数
        // divide：除法
        BigDecimal dive = diveHours.divide(new BigDecimal(2.5), 11, RoundingMode.HALF_UP);
        System.out.println(dive);
    }
}
