package com.xm.jy.test.jdk.string;

import java.util.Calendar;
import java.util.Date;

/**
 * @author: albert.fang
 * @date: 2021/3/12 13:05
 * @description: 格式化打印
 */
public class Printf {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    /**
     * Byte、Short、Integer、Long、BigInteger
     */
    private static void test1(){
        System.out.printf("数字10的十进制是：%d \n",10);
        System.out.printf("数字10的八进制是：%o \n",10);
        System.out.printf("数字10的十六进制是：%x \n",10);
        System.out.printf("数字10的十六进制是（转大写）：%X \n",10);
    }

    /**
     * Float、Double、BigDecimal
     */
    private static void test2(){
        // 科学计数法，保留6位小数
        System.out.printf("%e \n",12222.229344);
        System.out.printf("%E \n",12222.229344);
        // 保留一位小数
        System.out.printf("%g \n",12222.299344);
        System.out.printf("%G \n",12222.299344);
        // 保留6位小数，不足的补0
        System.out.printf("%f \n",123456.2371116);
        // 将小数转成16进制
        System.out.printf("%a \n",34343.23);
        System.out.printf("%A \n",34343.23);
    }

    /**
     * Character, Byte, Short, Integer
     */
    private static void test3(){
        System.out.printf("%c \n",'a');
        System.out.printf("%c \n",97);
        System.out.printf("%c \n",65);
        System.out.printf("%C \n",97);
    }

    /**
     * java.util.Date, java.util.Calendar, long
     * %t 后面还要接一个字符，具体是啥字符对应啥格式，查看链接：https://blog.csdn.net/zhanggonglalala/article/details/89453073
     */
    private static void test4(){
        System.out.printf("%tc \n", Calendar.getInstance().getTime());
        System.out.printf("%tF \n", Calendar.getInstance().getTime());
        System.out.printf("%tc \n", System.currentTimeMillis());
        System.out.printf("%tc \n",new Date());
    }

    /**
     *  if (arg.TYPE != boolean) return boolean
     *  if (arg != null) return true; else return false;
     */
    private static void test5(){
        System.out.printf("%b \n","not null");
        System.out.printf("%b \n",23);
        System.out.printf("%b \n",34.234);
        System.out.printf("%b \n",null);
        System.out.printf("%B \n",true);
        System.out.printf("%B \n",false);
    }

    /**
     * if (arg instanceof Formattable) arg.formatTo()
     * else arg.toString();
     */
    private static void test6(){
        System.out.printf("%s \n","wE都快两年了");
        System.out.printf("%S \n","we都快两年了");
    }

    /**
     * arg.hashCode()
     */
    private static void test7(){
        System.out.printf("%h",new Date());
        System.out.printf("%H",1);
        System.out.printf("%H",2);
    }
}
