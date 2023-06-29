package com.xm.jy.test.tools.jsoup.taobao;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class ThumbnailUtil {

    /**
     * 根据指定大小和指定经度压缩图片
     *
     * @param srcPath     源图片地址
     * @param desPath     目标图片地址
     * @param desFileSize 指定图片大小，单位kb
     * @param accuracy    精度，递归压缩的比率，建议小于0.9
     * @return
     */
    public static String compressPictureForScale(String srcPath, String desPath, long desFileSize, double accuracy) {
        if (StringUtils.isEmpty(srcPath) || StringUtils.isEmpty(desPath)) {
            return null;
        }
        if (!new File(srcPath).exists()) {
            return null;
        }
        try {
            File srcFile = new File(srcPath);
            long srcFileSize = srcFile.length();
            System.out.println("源图片: " + srcPath + ", 大小: " + srcFileSize / 1024 + "kb");
            //1.先转换成jpg
            Thumbnails.of(srcPath).scale(1f).toFile(desPath);
            //递归压缩，直到目标文件大小小于desFileSize
            compressPicCycle(desPath, desFileSize, accuracy);

            File desFile = new File(desPath);
            System.out.println("目标图片: " + desPath + ", 大小: " + desFile.length() / 1024 + "kb");
            System.out.println("图片压缩完成!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return desPath;
    }

    private static void compressPicCycle(String desPath, long desFileSize, double accuracy) throws IOException {
        File srcFileJPG = new File(desPath);
        long srcFileSizeJPG = srcFileJPG.length();
        //2.判断大小，如果小于50kb，不用压缩，如果大于等于50kb,需要压缩
        if (srcFileSizeJPG <= desFileSize * 1024) {
            return;
        }
        //计算宽高
        BufferedImage bim = ImageIO.read(srcFileJPG);
        int srcWidth = bim.getWidth();
        int srcHeight = bim.getHeight();
        int destWidth = new BigDecimal(srcWidth).multiply(new BigDecimal(accuracy)).intValue();
        int destHeight = new BigDecimal(srcHeight).multiply(new BigDecimal(accuracy)).intValue();
        Thumbnails.of(desPath).size(destWidth,destHeight).outputQuality(accuracy).toFile(desPath);
        compressPicCycle(desPath,desFileSize,accuracy);
    }

    public static void main(String[] args) {
        String srcPath = "C:\\Users\\hemanman6\\Desktop\\微信图片_20210802151348.png";
        String desPath = "C:\\Users\\hemanman6\\Desktop\\2.jpg";
        ThumbnailUtil.compressPictureForScale(srcPath,desPath,50,0.8);
    }
}

