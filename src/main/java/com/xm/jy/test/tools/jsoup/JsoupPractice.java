package com.xm.jy.test.tools.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author: albert.fang
 * @date: 2021/3/18 17:40
 * @description: Jsoup工具的练习：解析html文件和生成html文件
 */
public class JsoupPractice {


    private static void jsoup() throws IOException {
        File htmlFile = new File("E:/IdeaProjects/practice/src/main/resources/static/html/self_test/table.html");
        Document parse = Jsoup.parse(htmlFile,"utf-8");
        // 1、根据元素标签读取节点html
        String body = parse.getElementsByTag("body").html();
        System.out.println(body);
        String script = parse.getElementsByTag("script").html();
        System.out.println(script);
//        System.out.println(parse.getElementsByTag("tr").html());
        System.out.println(parse.getElementsByTag("tr").first().html());
        System.out.println(parse.getElementsByTag("tr").last().html());
        System.out.println(parse.getElementsByTag("tr").eq(2).html());
        // 2、根据ID来获取节点html
        System.out.println(parse.getElementById("firstTr").html());
        // 3、根据class来获取节点
        System.out.println(parse.getElementsByClass("othersTr").html());

    }

    @Test
    void testJsoup() throws IOException {
        File htmlFile = new File("E:/IdeaProjects/practice/src/main/resources/static/html/self_test/jsoup.html");
        Document document = Jsoup.parse(htmlFile, "utf-8");
        Element element = document.select("#results .report-table").get(0);
        element.select("tr:nth-child(3)").append("<td>asf</td><td>asf</td><td>sfd</td>");
        System.out.println(element.select("tr:nth-child(3)"));
//        System.out.println(document.select("#results .report-table").get(0));
    }
}
