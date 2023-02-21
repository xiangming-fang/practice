package com.xm.jy.test.jdk.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 13:31
 * 将一个文件的内容传到另一个文件里去；操作对象还是文件内容
 */
public class ReaderAndWriterTest {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("D://304//interview.txt");
        FileWriter fw = new FileWriter("D://304//23.txt");
        char[] chars = new char[3];
        int len;
        while(( len = fr.read(chars)) != -1){
            // 将输入流中读取的chars数组，读取数组中0到len的数据写到输出流
            fw.write(chars,0,len);
            // 读取chars数组中的数据，包括空位，写到输出流
//            fw.write(chars);
            // 将chars数组转成字符串写到输出流。
//            fw.write(chars.toString());
        }
        fr.close();
        fw.close();
    }
}
