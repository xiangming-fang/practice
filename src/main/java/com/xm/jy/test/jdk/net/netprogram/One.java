package com.xm.jy.test.jdk.net.netprogram;

import java.io.IOException;
import java.net.*;

/**
 * @ClassName: OneClass
 * @Description: description of class
 * @Author: Albert
 * @CreateDate: 2020/1/7 13:29
 */
public class One {
    public static void main(String[] args) {
        try {
            // 根据主机名来获取对应的InetAddress实例
            InetAddress ip = InetAddress.getByName("www.baidu.com");
            // 判断是否可达
            try {
                System.out.println("可达吗？"+ip.isReachable(2000));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 获取该InetAddress实例的Ip字符串
            System.out.println(ip.getHostAddress());
            // 根据原始ip地址来获取对应的InetAddress实例
            InetAddress local = InetAddress.getByAddress(
                    new byte[]{127,0,0,1}
            );
            try {
                System.out.println("本机是否可达:"+local.isReachable(2000));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 获取该InetAddress实例对应的全限定域名
            System.out.println(local.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
