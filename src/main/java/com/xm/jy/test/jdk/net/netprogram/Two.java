package com.xm.jy.test.jdk.net.netprogram;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @ClassName: Two
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/7 13:56
 */
public class Two {
    public static void main(String[] args) {
        String encode = null;
        try {
            encode = URLEncoder.encode("方翔鸣","GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(encode);
        String decode = null;
        try {
            decode = URLDecoder.decode(encode,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(decode);
    }
}
