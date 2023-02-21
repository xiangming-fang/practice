package com.xm.jy.test.jdk.io;

import java.io.*;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 12:57
 * 向文件中写入内容；操作对象是文件的内容
 */
public class OutputStreamOrWriterTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D://304//interview.txt");
        System.out.println(file.exists());
        FileOutputStream fos = new FileOutputStream("D://304//interview.txt",true);
        String s = "232434";
        fos.write(s.getBytes(),0,s.getBytes().length);
        fos.close();

        System.out.println("************");

        FileWriter fw = new FileWriter("D://304//interview.txt",true);
        String name = "what is fuck";
        fw.write(name);
        fw.close();
    }
}
