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
    public static void main(String[] args) throws Exception{

        File file = new File("F:\\软考-系统架构设计师");
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
