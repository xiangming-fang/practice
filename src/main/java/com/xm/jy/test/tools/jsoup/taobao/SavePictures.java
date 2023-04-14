package com.xm.jy.test.tools.jsoup.taobao;

import com.xm.jy.test.tools.FileUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * @Author: xiangming.fang
 * @Date: 2023/4/14 11:10
 * 从 https://item.taobao.com/item.htm?spm=a1z10.3-c-s.0.0.6e10cfc7Fmcf3E&id=696546022780 保存图片
 * 由于 类似 item.taobao.com 需要登录才能访问，所以我们打开指定宝贝页面，copy出html，然后用jsoup读取指定html，从而下载
 * 5个主图以及颜色种类图
 * 垃圾淘宝，非得登录才能访问宝贝详情
 *
 */
public class SavePictures {

    private static String main_pic_id = "J_UlThumb";

    private static String color_pic_class = "J_TSaleProp tb-img tb-clearfix";

    private static String protocol = "https:";

    // todo 修改下这个
    private static String savePath = "E:\\avif\\";

    private static String suffix = ".jpg";

    private static String color = "_400x400.jpg";

    private static String tilte = "J_Title";

    public static void main(String[] args) throws IOException {

        // todo 这个路径也换一下咯
        File htmlFile = new File("D:\\study\\practice\\src\\main\\resources\\taobao\\item.taobao.html");
        Document parse = Jsoup.parse(htmlFile,"utf-8");


//        File htmlFile = new File("D:\\study\\practice\\src\\main\\resources\\taobao\\1.htm");
//        Document parse = Jsoup.parse(htmlFile,"gbk");

//        HttpURLConnection inputStreamFromUrl = FileUtils.getInputStreamFromUrl("https://item.taobao.com/item.htm?spm=a1z10.3-c-s.0.0.6e10cfc7Fmcf3E&id=704061000469");
//        InputStream is = inputStreamFromUrl.getInputStream();
//        Document parse = Jsoup.parse(is, "utf-8", "https://item.taobao.com");


        // 宝贝title作为一层路径
        Element title = parse.getElementById(tilte);
        String baby;
        if (title != null){
            baby = title.getElementsByClass("tb-main-title").get(0).attr("data-title");
            baby = baby.substring(0,12);
            savePath = savePath + baby + "\\";
        }

        Element elementById = parse.getElementById(main_pic_id);
        // 主图
        if (elementById != null){
            Elements img = elementById.getElementsByTag("img");
            int size = img.size();
            for (int i = 0; i < size; i++) {
                String attr = img.get(i).attr("data-src");
                attr = attr.substring(0,attr.length() - 10);
                FileUtils.download(protocol + attr,savePath + (i + 1) + suffix);
            }
        }
        // 颜色
        Elements elementsByClass = parse.getElementsByClass(color_pic_class);
        if (CollectionUtils.isNotEmpty(elementsByClass)){
            Element colorNode = elementsByClass.get(0);
            Elements lis = colorNode.getElementsByTag("li");
            for (Element element : lis) {
                String attr = element.getElementsByTag("a").get(0).attr("style");
                int s = attr.indexOf("/");
                int l = attr.indexOf("jpg") + 3;
                attr = attr.substring(s,l);
                attr = attr + color;
                String colorText = element.getElementsByTag("span").text();
                FileUtils.download(protocol + attr, savePath + colorText + suffix);
            }
        }
    }
}
