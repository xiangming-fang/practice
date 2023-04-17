package com.xm.jy.test.tools.jsoup.taobao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
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

    private static final String zh = "佐晗";

    private static final String tono = "TONO";

    private static final String oleana = "OLEANA";

    private static final List<String> babys = new ArrayList<String>(){
        {
            add(zh);
            add(tono);
            add(oleana);
        }
    };

    public static void main(String[] args) {
        File file = new File(s_path);
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        t_path = t_path + sdf.format(new Date()) + "\\";
        // key -> 产品名；value -> 对应的产品下图片
        Map<String, List<File>> map = Arrays.stream(files).collect(Collectors.groupingBy(v -> v.getName().split(delimiter)[0]));
        map.forEach((k,v) -> {
            String productName = t_path;
            if (k.contains(zh)){
                productName = productName + zh + "\\" + k;
            }
            else if (k.contains(tono)){
                productName = productName + tono + "\\" + k;
            }
            else if (k.contains(oleana)){
                productName = productName + oleana + "\\" + k;
            }
            else {
                System.err.println("宝贝名称不符合预期，需要额外处理");
                return;
            }
            File parent = new File(productName);
            if (!parent.exists()){
                parent.mkdirs();
            }
            for (File ff : v) {
                if (!ff.renameTo(new File(productName + "\\" + ff.getName().split(delimiter)[1]))) {
                    // 备选方案
                    Random random = new Random();
                    int i = random.nextInt(100) + 10;
                    ff.renameTo(new File(productName + "\\" + i + ff.getName().split(delimiter)[1]));
                }
            }
        });
    }

}
