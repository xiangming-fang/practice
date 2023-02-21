package com.xm.jy.test.jdk.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 12:44
 * 创建文件，创建目录等操作，操作对象是所有文件相关
 */
@Slf4j
public class FileTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D://304");
        System.out.println(file.mkdir());
        System.out.println(file.isDirectory());
        File document = new File("D://304/23.txt");
        System.out.println(document.createNewFile());
        File file1 = new File("D://304//305//306");
        System.out.println(file1.mkdirs());
        for (File listFile : file.listFiles()) {
            System.out.println(listFile);
        }
        for (String s : file.list()) {
            System.out.println(s);
        }
        File interview = new File("D://304//interview.txt");
        System.out.println(interview.createNewFile());
    }

    @Test
    void testParentFileList() throws InterruptedException {
        // multiThread
        File file = new File("E:\\parentFile\\file.txt");
        if (file.exists()){
            TimeUnit.SECONDS.sleep(10);
            for (File listFile : Objects.requireNonNull(file.getParentFile().listFiles())) {
                log.info(listFile.getAbsolutePath());
            }
        }
    }

    @Test
    void testFileList(){
        // 给parentFile无权限读取
        File file = new File("E:\\parentFile\\file.txt");
        if (file.exists()){
            for (File listFile : Objects.requireNonNull(file.getParentFile().listFiles(),"listfiles 不能为空")) {
                log.info(listFile.getAbsolutePath());
            }
        }
    }

    @Test
    void testFileListWhenFolder(){
        System.out.println(3/(double)2);
    }

    /**
     * 将double四色五入保留一位小数之后，转成百分号字符串
     */
    public static String convertDoubleToPercent(double doubleValue){
        return String.format("%.1f%s", doubleValue * 100,"%");
    }

    @Test
    void testDouble(){
        System.out.println(convertDoubleToPercent(0.0654));
    }
}
