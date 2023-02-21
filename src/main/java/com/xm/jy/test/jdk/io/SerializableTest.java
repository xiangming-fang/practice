package com.xm.jy.test.jdk.io;

import java.io.*;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 17:30
 * 序列化测试，将程序运行的对象保存到文件里
 */
public class SerializableTest implements Serializable {
    private int a = 9;

    public int getA() {
        return a;
    }

    public static void main(String[] args) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                "D://304//23.txt"));
        oos.writeObject(new SerializableTest());
        oos.close();
    }
}
