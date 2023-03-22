package com.xm.jy.test.jdk.io;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.jdk.io
 * @ClassName: Rename
 * @Author: albert.fang
 * @Description: 修改文件名
 * @Date: 2023/3/22 20:45
 */
public class Rename {

    public static void main(String[] args) throws Exception {

        String path = "F:\\软考-系统架构设计师";
//        String path = "F:\\1--【软考】系统架构设计师-考试介绍";

        System.out.printf("重命名之前有 %d 个文件\n",count(new File(path)));
        rename(path);
        System.out.printf("重命名之后有 %d 个文件\n",count(new File(path)));
    }


    public static int count(File file){
        int res = 0;
        if (Objects.nonNull(file.listFiles())){
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    res += count(f);
                }else {
                    res ++;
                }
            }
        }
        return res;
    }



    public static void rename(String path) throws Exception{

        File file = new File(path);
        if (file.isDirectory()) {
            LinkedList<File> queue = new LinkedList<>();
            queue.offer(file);
            while (!queue.isEmpty()){
                int len = queue.size();
                for (int i = 0; i < len; i++) {
                    File poll = queue.poll();
                    if (Objects.nonNull(poll) && Objects.nonNull(poll.listFiles())){
                        for (File f : poll.listFiles()) {
                            if (Objects.nonNull(f)){
                                if (f.isDirectory()) {
                                    queue.offer(f);
                                }else {
                                    String fname = f.getAbsolutePath();
                                    f.renameTo(new File(fname.replace("【瑞客论坛 www.ruike1.com】","")));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
