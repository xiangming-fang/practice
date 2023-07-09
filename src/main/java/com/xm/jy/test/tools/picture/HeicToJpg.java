package com.xm.jy.test.tools.picture;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

import java.io.File;
import java.io.IOException;

/**
 * @Author: albert.fang
 * @Date: 2023/7/8 18:20
 */
public class HeicToJpg {

    // image magick 安装目录
    private static final String IMAGE_MAGICK_PATH = "C:\\Program Files\\ImageMagick-7.1.1-Q16-HDRI";

    private static final ConvertCmd cmd = new ConvertCmd();

    static {
        cmd.setSearchPath(IMAGE_MAGICK_PATH);
    }

    public static void main(String[] args) {

        heicToJpg("E:\\宝贝\\20230708\\佐晗\\ZUOHAN佐晗8517工装后\\1.jpg","E:\\宝贝\\20230708\\佐晗\\ZUOHAN佐晗8517工装后\\1.jpg");

    }

    // 将file文件下的所有文件夹下的heic文件递归成jpg格式
    public static void batchHeicToJpg(File file){
        if (!file.isDirectory()) {
            return;
        }
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                batchHeicToJpg(listFile);
            }else {
                heicToJpg(listFile.getPath(),listFile.getPath());
            }
        }
    }

    // src heic 格式源文件
    // dest 目标图片文件地址
    public static void heicToJpg(String src,String dest){

        IMOperation op = new IMOperation();
        op.addImage(src);
        op.addImage(dest);

        try {
            cmd.run(op);
        } catch (IOException | InterruptedException | IM4JavaException e) {
            System.err.println(src);
            e.printStackTrace();
        }
    }



}
