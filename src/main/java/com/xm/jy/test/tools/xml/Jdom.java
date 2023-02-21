package com.xm.jy.test.tools.xml;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.util.List;

public class Jdom {
    public static void main(String[] args) throws Exception {
        //构建加载器，加载xml文档
        SAXBuilder builder = new SAXBuilder();
        Document document = builder.build("E:\\IdeaProjects\\timer\\src\\main\\java\\com\\cx\\timer\\xml\\Three.xml");
        //获取根节点
        Element root = document.getRootElement();
        //通过根节点获取子节点的集合
        List childList = root.getChildren("student");
        //遍历集合
        for (int i = 0; i < childList.size(); i++) {
            Element childs = (Element) childList.get(i);
            //通过上一级节点获取子节点
            List child = childs.getChildren();
            for (int j = 0; j < child.size(); j++) {
                System.out.println(((Element) child.get(j)).getName()
                        + ":" + ((Element) child.get(j)).getValue());
            }
                System.out.println();
            }
    }
}