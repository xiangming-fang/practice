package com.xm.jy.test.jdk.lambda;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.lambda
 * @ClassName: FindAndMatchTest
 * @Author: albert.fang
 * @Description: find and match
 * @Date: 2021/7/9 14:04
 */
class FindAndMatchTest {

    private static List<String> collection = Stream.of("abc","abd","abe","abf","abg").collect(Collectors.toList());

    @Test
    void noneMatch(){
        boolean result = collection.stream().noneMatch(v -> v.contains("z"));
        boolean result1 = collection.stream().noneMatch(v -> v.contains("a"));
        System.out.println("none element contain 'z' is :" + result);
        System.out.println("none element contain 'a' is :" + result1);
    }

    @Test
    void allMatch(){
        boolean result = collection.stream().allMatch(v -> v.contains("a"));
        boolean result2 = collection.stream().allMatch(v -> v.contains("g"));
        System.out.println("all element contain 'a' is :" + result);
        System.out.println("all element contain 'g' is :" + result2);
    }

    @Test
    void anyMatch(){
        boolean result = collection.stream().anyMatch(v -> v.contains("a"));
        boolean result2 = collection.stream().anyMatch(v -> v.contains("g"));
        boolean result3 = collection.stream().anyMatch(v -> v.contains("h"));
        System.out.println("has element contain 'a' is :" + result);
        System.out.println("has element contain 'g' is :" + result2);
        System.out.println("has element contain 'h' is :" + result3);
    }


    @Test
    void findFirst(){
        collection.stream().filter(v -> v.contains("a")).findFirst().ifPresent(System.out::println);
        collection.stream().filter(v -> v.contains("e")).findFirst().ifPresent(System.out::println);
    }

    @Test
    void findAny(){
        collection.stream().filter(v -> v.contains("a")).findAny().ifPresent(a -> System.out.println("has element contain 'a' is : true"));
        collection.stream().filter(v -> v.contains("g")).findAny().ifPresent(a -> System.out.println("has element contain 'g' is : true"));
        collection.stream().filter(v -> v.contains("h")).findAny().ifPresent(a -> System.out.println("has element contain 'h' is : true"));
        if (collection.stream().filter(v -> v.contains("b")).findAny().isPresent()) {
            System.out.println("has element contain 'b' is : true");
        }
    }

}
