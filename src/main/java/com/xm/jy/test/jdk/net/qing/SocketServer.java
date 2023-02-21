package com.xm.jy.test.jdk.net.qing;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try {
            /*
             * 1、创建serverScoket对象 
             * 2、调用accept()方法开启监听 
             * 3、获取文件流（输出流或输入流） 
             * 4、执行读写操作
             * 5、关闭流 
             * 6、关闭serversocket。
             */
            ServerSocket server = new ServerSocket(8888);
            System.out.println("服务器已经启动！");
            // 接收客户端发送的信息
            Socket socket = server.accept();

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String info = null;
            while ((info = br.readLine()) != null) {
                System.out.println(info);
            }

            // 向客户端写入信息
            OutputStream os = socket.getOutputStream();
            String str = "欢迎登陆到server服务器!";
            os.write(str.getBytes());

            // 关闭文件流
            os.close();
            br.close();
            is.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}