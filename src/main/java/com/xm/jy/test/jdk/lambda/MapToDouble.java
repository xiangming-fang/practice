package com.xm.jy.test.jdk.lambda;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: albert.fang
 * @date: 2021/3/11 17:32
 * @description: lambda表达式之mapToDouble api使用（求和、最大、最小、平均）
 */
public class MapToDouble {

    @Test
    void test1(){
        ArrayList<Double> doubles = new ArrayList<>();
        doubles.add(2.3);
        doubles.add(2.3);
        doubles.add(2.3333);
        doubles.add(2.3333);
        doubles.add(2.3563);
        doubles.add(2.7863);
        doubles.add(2.3333);
        List<Double> sortedDoubles = doubles.stream().sorted(Double::compareTo).collect(Collectors.toList());
        Collections.reverse(sortedDoubles);
        double asDouble = doubles.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
        System.out.println(doubles.indexOf(asDouble));
        System.out.println(getIndex(doubles, asDouble));
        System.out.println(doubles.stream().mapToDouble(Double::doubleValue).max().orElse(0.0));
        System.out.println(doubles.stream().mapToDouble(Double::doubleValue).min().orElse(0.0));
        System.out.println(doubles.stream().mapToDouble(Double::doubleValue).max().orElse(0.0) == doubles.stream().mapToDouble(Double::doubleValue).min().orElse(0.0));
        System.out.println(doubles.stream().mapToDouble(Double::doubleValue).average().orElse(0.0));
    }

    public static <T extends Comparable<T>> List<Integer> getIndex(List<T> ts,T t){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < ts.size(); i++) {
            if(ts.get(i).compareTo(t) == 0){
                result.add(i);
            }
        }
        return result;
    }

    @Test
    void test2(){
        ArrayList<String> stringDoubles = new ArrayList<>();
        stringDoubles.add("23.2231");
        stringDoubles.add("23.2332");
        stringDoubles.add("23.2233");
        Collections.reverse(stringDoubles);
        System.out.println(stringDoubles.toString());
        System.out.println(stringDoubles.stream().map(Double::new).mapToDouble(Double::doubleValue).sum());
        System.out.println(stringDoubles.stream().map(Double::new).mapToDouble(Double::doubleValue).max());
        System.out.println(stringDoubles.stream().map(Double::new).mapToDouble(Double::doubleValue).min());
        System.out.println(stringDoubles.stream().map(Double::new).mapToDouble(Double::doubleValue).average());
    }

    @Test
    void testPrecision(){
        System.out.println(formatDigit(12.2, 2));
        System.out.println(formatDigit(12.234, 2));
        System.out.println(formatDigit(12.256, 2));
        double a = 2.345,b = 2.345;
        if (a == b) {
            System.out.println("想的呢个");
        }
        String aa = null;
        set(aa);
        System.out.println(aa);
    }

    private static double formatDigit(double num, int decimalPlace) {
        DecimalFormat fm = null;
        switch (decimalPlace) {
            case 0:
                fm = new DecimalFormat("##");
                break;
            case 1:
                fm = new DecimalFormat("##.#");
                break;
            case 2:
                fm = new DecimalFormat("##.##");
                break;
            case 3:
                fm = new DecimalFormat("##.###");
                break;
            case 4:
                fm = new DecimalFormat("##.####");
                break;
            default:
                break;
        }

        if (fm == null) {
            return num;
        }

        StringBuffer sbf = new StringBuffer();
        fm.format(num, sbf, new FieldPosition(java.text.NumberFormat.FRACTION_FIELD));
        return Double.parseDouble(sbf.toString());
    }

    private void set(String aa){
        aa = "sfsdf";
    }

}
