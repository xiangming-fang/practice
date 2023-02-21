package com.xm.jy.test.jdk.io.inputoutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @ClassName: FolderCopyAndPaste
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2019/12/31 17:28
 */
public class FolderCopyAndPaste {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("E:\\inputandoutput");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\");
    }
}
