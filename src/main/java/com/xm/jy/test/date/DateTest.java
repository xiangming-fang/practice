package com.xm.jy.test.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.date
 * @ClassName: DateTest
 * @Author: albert.fang
 * @Description:
 * @Date: 2022/11/3 16:29
 */
public class DateTest {


    public static void main1(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now.plusDays(1).equals(now.plusDays(1)));
        System.out.println(now.plusDays(1).equals(now));
        System.out.println(now);
        LocalDateTime localDateTime = now.atTime(0, 0, 0);
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        LocalDateTime localDateTime1 = now.atTime(23, 59, 59);
        System.out.println(localDateTime1);
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toLocalTime());
        System.out.println(now.toLocalTime().truncatedTo(ChronoUnit.MINUTES));
    }

}
