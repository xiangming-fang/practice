package com.xm.jy;

import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerFactory;
import java.util.HashMap;
import java.util.List;

/**
 * @author: albert.fang
 * @date: 2021/3/15 11:25
 * @description: 像素点密度的计算方式： （横方向的像素大小的平方 + 纵方向的像素大小的平方）开根（也就是屏幕对角线的像素）最后除以屏幕尺寸（比如15.6英寸）
 */
public class PPI {
    public static void main(String[] args) {
        System.out.printf("公司电脑屏幕像素点密度ppi是：%s \n",getPPI());
    }

    private static double getPPI(){


        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("jdk.xml.xpathTotalOpLimit", "1000");



        double x = 1920;
        double y = 1080;
        double xPow = Math.pow(x, 2);
        double yPow = Math.pow(y, 2);
        double bevel = Math.sqrt(xPow + yPow);
        System.out.printf("斜边像素数是%f \n",bevel);
        return bevel / 15.6;
    }



}
