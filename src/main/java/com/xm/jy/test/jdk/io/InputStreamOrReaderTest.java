package com.xm.jy.test.jdk.io;

import java.io.*;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 13:05
 * 把文件的内容读取到程序来，操作对象也是文件里的内容；
 */
public class InputStreamOrReaderTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D://304//interview.txt");
        byte[] bytes = new byte[3];
        int len;
        while((len = (fis.read(bytes))) != -1){
            System.out.println(len);
            System.out.println(new String(bytes,0,len));
        }
        fis.close();
        System.out.println("************");
        FileReader fr = new FileReader("D://304//interview.txt");
        char[] chars = new char[2];
        int clen;
        while((clen = fr.read(chars)) != -1){
            System.out.println(new String(chars,0,clen));
        }
        fr.close();
    }
}
