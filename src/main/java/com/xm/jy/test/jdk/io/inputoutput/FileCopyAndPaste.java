package com.xm.jy.test.jdk.io.inputoutput;

import java.io.*;

/**
 * @ClassName: FileCopyAndPaste
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2019/12/31 17:06
 */
public class FileCopyAndPaste {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("F:\\2.txt",true);
        fileWriter.write("今天是2020年的第二天");
        fileWriter.close();
    }
}
