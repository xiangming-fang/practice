package com.xm.jy.test.jdk.io;

import java.io.*;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 17:36
 * 反序列化：将文件中的对象数据读取到程序里，转换成对象
 */
public class DeSerializableTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D" +
                "://304//23.txt"));
        SerializableTest serializableTest = (SerializableTest)ois.readObject();
        System.out.println(serializableTest.getA());
        System.out.println(serializableTest.getClass().getDeclaredField("a"));
    }
}
