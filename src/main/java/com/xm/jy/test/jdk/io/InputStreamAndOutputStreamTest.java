package com.xm.jy.test.jdk.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 13:22
 * 一个文件中的内容写到另一个文件中；操作对象还是文件内容
 */
public class InputStreamAndOutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D://304//interview.txt");
        FileOutputStream fos = new FileOutputStream("D://304//23.txt");
        byte[] bytes = new byte[6];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes,0,len);
        }
        fis.close();
        fos.close();
    }
}
