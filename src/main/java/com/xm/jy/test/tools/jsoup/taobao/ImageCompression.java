package com.xm.jy.test.tools.jsoup.taobao;

import java.io.IOException;

/**
 * @Author: xiangming.fang
 * @Date: 2023/6/27 14:13
 */
public class ImageCompression {
    public static void main(String[] args) throws IOException {
        String s = "E:\\宝贝\\20230627\\欧货\\欧货2023春夏新款彩色贴标窄\\1.jpg";
        String t = "E:\\宝贝\\20230627\\欧货\\欧货2023春夏新款彩色贴标窄\\11.jpg";
        String path1 = "E:\\宝贝\\20230627\\欧货\\欧货2023春夏新款彩色贴标窄\\compression";
        ThumbnailUtil.compressPictureForScale(s,t,300L,0.9);
    }
}
