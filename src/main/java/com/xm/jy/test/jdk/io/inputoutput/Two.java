package com.xm.jy.test.jdk.io.inputoutput;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName: Two
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2019/12/31 16:54
 */
public class Two {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\inputandoutput\\6.txt");
        byte[] b = new byte[1024];
        // 用来保存实际读取的字节数
        int len = 0;
        while ((len = fileInputStream.read(b)) != -1){
            // 将字符数组转成字符串输出
            System.out.println(new String(b,0,len));
        }
        fileInputStream.close();
    }
}
