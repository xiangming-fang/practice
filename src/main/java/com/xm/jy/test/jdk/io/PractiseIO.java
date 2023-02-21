package com.xm.jy.test.jdk.io;

import java.io.*;

/**
 * @auther 方翔鸣
 * @date 2020/3/7 13:08
 * IOTest:将一个目录下（D:\304\307source）的所有.java文件复制到另外一个文件下（D:\304\308target），并将
 * .java改成.jad。
 */
public class PractiseIO {

    public static void main(String[] args) throws IOException {
        File fileSource = new File("D://304//307source");
        recurrenceFile(fileSource);
    }

    public static void recurrenceFile(File file) throws IOException {
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()){
                recurrenceFile(listFile);
            }else if (listFile.getName().endsWith(".java")){
                StringBuilder fileName = new StringBuilder(listFile.getName().substring(0,
                        listFile.getName().indexOf(".")));
                StringBuilder sb = fileName.append(".jad");
                StringBuilder targetFileName = new StringBuilder("D://304//307target//");
                StringBuilder finalFileName = targetFileName.append(sb);
                File file1 = new File(String.valueOf(finalFileName));
                if (!file1.exists()){
                    file1.createNewFile();
                }
                FileReader fr = new FileReader(listFile);
                FileWriter fw = new FileWriter(file1,true);
                char[] chars = new char[512];
                int len;
                while((len = fr.read(chars)) != -1){
                    fw.write(chars,0,len);
                }
                fr.close();
                fw.close();
            }
        }
    }
}
