package com.xm.jy.test.jdk.lambda;

import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: albert.fang
 * @date: 2021/3/11 18:32
 * @description: lambda表达式之reduce api使用：reduce对流中的元素进行一些指定操作
 */
public class Reduce {
    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1(){
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).reduce(1, (result, value) -> {
            result += value;
            return result;
        }));
    }

    private static void test2(){
        System.out.println(Stream.of("我", "爱", "北", "京", "天", "安", "门").reduce("", (init, value) -> {
            init += value;
            return init;
        }));
    }

    private static void test3(){
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6).reduce((var1, var2) -> {
            System.out.printf("%d-%d%s",var1,var2,",");
            return var1 + var2;
        }));
    }

    private static void test4(){
        System.out.println(Stream.of(1, 3, 5, 7, 9).reduce(Integer::sum));
        System.out.println(Stream.of(1, 3, 5, 7, 9).reduce(Integer::max));
        System.out.println(Stream.of(1, 3, 5, 7, 9).reduce(Integer::min));
    }

    @Test
    void test(){
        System.out.println(convertDoubleToPercent(0.0003d));
        System.out.println(convertDoubleToPercent(0.0006d));

        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("A",1);
        System.out.println(hashMap.get("B") == null);
        List<String> collect = Stream.of("1", "3", "<6%").collect(Collectors.toList());
        if (collect.contains("<6%")) {
            collect.remove("<6%");
        }
        System.out.println(collect.toString());
    }

    /**
     * 将double四色五入保留一位小数之后，转成百分号字符串
     */
    public static String convertDoubleToPercent(double doubleValue){
        if (doubleValue > 0 && doubleValue < 0.0005 ){
            return String.format("%s","<0.1%");
        }
        return String.format("%.1f%s", doubleValue * 100,"%");
    }

    @Test
    void testReduce(){
        List<String> aa = new ArrayList<>();
        aa.add("34.5%");
        aa.add("14.5%");
        aa.add("454.5%");
        aa.add("324.5%");
        System.out.println(getMaxFromStrPercent(aa));
    }

    private static String getMaxFromStrPercent(List<String> percents){
        return percents.stream().map(v -> new Double(v.substring(0, v.length() - 1))).mapToDouble(Double::doubleValue).max().getAsDouble() + "%";
    }


    @Test
    void testToMap(){
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("sdf");
            student.setSex("asdf");
            student.setAge(i);
            students.add(student);
        }
        Student student = new Student();
        student.setAge(6);
        student.setName("fsdfsf");
        student.setSex("jljklklj");
        students.add(student);
        Map<Integer, String> collect = students.stream().collect(Collectors.toMap(Student::getAge, Student::getName,(v1,v2) -> v1));
        System.out.println(collect.toString());
    }
}

@Data
class Student{
    private String name;
    private String sex;
    private Integer age;
}
