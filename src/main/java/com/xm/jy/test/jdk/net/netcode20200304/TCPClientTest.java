package com.xm.jy.test.jdk.net.netcode20200304;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 18:38
 * socket 客户端
 */
public class TCPClientTest {
    public static void main(String[] args) throws IOException {
        while (true){
            Socket socket = new Socket("127.0.0.1",7200);
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            Scanner input = new Scanner(System.in);
            String name = input.nextLine();
            os.write(name.getBytes(),0,name.getBytes().length);
            byte[] bytes = new byte[1024];
            int len;
            while((len = is.read(bytes)) != -1){
                System.out.println(new String(bytes,0,len));
            }
            is.close();
            os.close();
            socket.close();
        }
    }
}
