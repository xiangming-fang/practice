package com.xm.jy.test.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: xiangming.fang
 * @Date: 2023/4/14 11:35
 */
public class FileUtils {


    // savePath 是到具体的文件名的不是到文件夹的
    public static void download(String url,String savePath){
        InputStream is = null;
        FileOutputStream fos = null;
        HttpURLConnection inputStreamFromUrl = null;
        try {
            inputStreamFromUrl = getInputStreamFromUrl(url);
            is = inputStreamFromUrl.getInputStream();
            File file = new File(savePath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()){
                parentFile.mkdirs();
            }
            fos = new FileOutputStream(file);
            byte[] buff = new byte[1024];
            // 每次读取多少
            int tmp;
            while ((tmp = is.read(buff)) != -1){
                fos.write(buff,0,tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamFromUrl != null){
                inputStreamFromUrl.disconnect();
            }
        }
    }

    // 根据url获取指定资源
    public static HttpURLConnection getInputStreamFromUrl(String url){
        HttpURLConnection urlConnection = null;
        try {
            URL picUrl = new URL(url);
            urlConnection = (HttpURLConnection)picUrl.openConnection();
            urlConnection.connect();
            return urlConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("url 读取不到资源");
    }

    public static void main(String[] args) {
        download("https://gd1.alicdn.com/imgextra/i4/2212570412101/O1CN01oTmT1q1ROIwQBWtXc_!!2212570412101.jpg","E:\\avif\\1.jpg");
    }
}
