package com.xm.jy.test.tools.jsoup.taobao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: xiangming.fang
 * @Date: 2023/4/14 18:08
 * 归类淘宝下载下来的图片
 */
public class ClassifyTaobaoPicture {

    private static String s_path = "D:\\downloads";

    // 后面加一层日期的，自动生成
    private static String t_path = "E:\\宝贝\\";

    private static String delimiter = "-";

    public static void main(String[] args) {
        File file = new File(s_path);
        File[] files = file.listFiles();
        if (files == null) return;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        t_path = t_path + sdf.format(new Date()) + "\\";
        // key -> 产品名；value -> 对应的产品下图片
        Map<String, List<File>> map = Arrays.stream(files).collect(Collectors.groupingBy(v -> v.getName().split(delimiter)[0]));
        map.forEach((k,v) -> {
            String productName = t_path + k;
            File parent = new File(productName);
            if (!parent.exists()){
                parent.mkdirs();
            }
            for (File ff : v) {
                ff.renameTo(new File(productName + "\\" + ff.getName().split(delimiter)[1]));
            }
        });
    }

}
