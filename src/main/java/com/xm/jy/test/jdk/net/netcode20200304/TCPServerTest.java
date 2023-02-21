package com.xm.jy.test.jdk.net.netcode20200304;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 18:39
 * socket 服务端
 */
public class TCPServerTest {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7200);
        while (true){
            Socket socket = serverSocket.accept();
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            len = is.read(bytes);
            System.out.println(new String(bytes,0,len));
            Scanner input = new Scanner(System.in);
            String message = input.nextLine();
            os.write(message.getBytes(),0,message.length());
            is.close();
            os.close();
            socket.close();
        }
    }
}
