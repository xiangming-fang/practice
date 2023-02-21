package com.xm.jy.test.jdk.io;

import java.io.*;
import java.util.ArrayList;

/**
 * @auther 方翔鸣
 * @date 2020/3/7 14:36
 * IO练习：
 * 从类似如下的文本文件中读取出所有的名字，并打印出重复的姓名和重复次数，并按重复次数排序。
 * 1，张三，24
 * 2，李四，23
 * 3，张三，56
 * 4，王五，23
 * 5，张三，28
 * 6，李四，28
 * 7，赵六，40
 * 8，田七，34
 */
public class PractiseIOTwo {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("D:\\IDEAproject\\timer\\src\\main\\java\\com\\cx\\timer\\io\\name.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<User> users = new ArrayList<>();
        String line;
        int count = 0;
        while ((line = br.readLine()) != null){
            User user = new User();
            if (count == 0){
                user.setName(line.split("，")[1]);
                user.setRepeatCount(1);
                users.add(user);
                count++;
            }else {
                boolean flag = true;
                for (User user1 : users) {
                    if (user1.getName().equals(line.split("，")[1])){
                        user1.setRepeatCount(user1.getRepeatCount() + 1);
                        flag = false;
                    }
                }
                if (flag){
                    user.setName(line.split("，")[1]);
                    user.setRepeatCount(1);
                    users.add(user);
                }

            }

        }
        FileWriter fw = new FileWriter("D:\\IDEAproject\\timer\\src\\main\\java\\com\\cx\\timer\\io\\name2.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (User user : users) {
            if (user.getRepeatCount() > 1){
                System.out.println(user.toString());
                bw.write(user.getName() + user.getRepeatCount());
                bw.newLine();
            }
        }
        br.close();
        bw.close();
    }
}
class User{
    private String name;
    private int repeatCount;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", repeatCount=" + repeatCount +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }
}
