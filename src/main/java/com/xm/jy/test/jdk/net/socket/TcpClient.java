package com.xm.jy.test.jdk.net.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpClient {  
      
    private static final int NUM = 3;
    public static void main(String args[]){  
        try {  
            Socket socket = new Socket("127.0.0.1", 4700);  
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));  
              
            PrintWriter os = new PrintWriter(socket.getOutputStream());  
              
            System.out.println("从客户端接收三角形的三条边发送到服务器：");  
            System.out.println("请输入3条边：");  
            String readline = "";  
            for (int i = 0; i < NUM; i++) {  
                os.flush();  
                readline = sin.readLine();  
                os.println(readline);  
            }  
              
            os.close();  
            socket.close();  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
      
}  