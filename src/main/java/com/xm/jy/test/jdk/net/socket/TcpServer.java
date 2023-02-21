package com.xm.jy.test.jdk.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {  
    private static final int NUM = 3;
    public static void main(String args[]){  
          
        TcpServer tcpServer = new TcpServer();  
        try {  
            ServerSocket server = null;
            try {  
                server = new ServerSocket(4700);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
              
            Socket socket = null;  
            try {  
                socket = server.accept();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
              
            String line = "";  
            double a[] = new double[NUM];  
            BufferedReader is = new BufferedReader(new InputStreamReader  
                    (socket.getInputStream()));  
              
            System.out.print("从客户端接收三角形的三条边：");
            for (int i = 0; i < a.length; i++) {  
                line = is.readLine();  
                a[i] = Double.parseDouble(line);
                System.out.print(a[i]+" ");
            }
            System.out.println(" ");
              
            System.out.println("三角形的面积是：" + tcpServer.countarea(a));  
              
            is.close();//关闭读取流  
            socket.close();//关闭Socket  
            server.close();//关闭ServerSocket  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
      
    //计算三角形面积的方法  
    public double countarea(double a[]){  
        double area = 0.0;
        double temp = 0.0;  
        double aside = 0.0;  
        double bside = 0.0;  
        double cside = 0.0;  
        aside = a[0];  
        bside = a[1];  
        cside = a[2];  
        temp = a[0] + a[1] + a[2];  
        temp = temp / 2;
        area = Math.sqrt(temp * (temp - aside) * (temp - bside) * (temp - cside));  
          
        return area;  
    }  
}  