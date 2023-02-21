package com.xm.jy.test.jdk.io.inputoutput;

import java.io.*;

/**
 * @ClassName: OneClass
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2019/12/30 14:26
 */
public class One {
    public static void main(String[] args) throws IOException {
        File fileDirectory = new File("E:\\inputandoutput\\2.txt");
        FileInputStream fileInputStream = new FileInputStream(fileDirectory);
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\inputandoutput\\4.txt",true);
        // 读取的数据保存在bytes数组中
        byte[] bytes = new byte[1024];
        // 用于保存实际读取的字节数
        int hasRead = 0;
        while ((hasRead = fileInputStream.read(bytes)) != -1){
            System.out.println(new String(bytes,0,hasRead));
            System.out.println(hasRead);
            fileOutputStream.write(bytes,0,hasRead);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
