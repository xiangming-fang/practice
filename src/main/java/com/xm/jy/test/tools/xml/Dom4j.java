package com.xm.jy.test.tools.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

public class Dom4j {
    public static void main(String[] args) {
        //加载解析器
        File inputXml = new File("E:\\IdeaProjects\\timer\\src\\main\\java\\com\\cx\\timer\\xml\\Three.xml");
        SAXReader saxReader = new SAXReader();
        try {
            //获取文档内容
            Document document = saxReader.read(inputXml);
            //获取根节点
            Element root = document.getRootElement();
            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element childList = (Element) i.next();
                for (Iterator j = childList.elementIterator(); j.hasNext();) {
                    Element node = (Element) j.next();
                    System.out.println(node.getName() + ":" + node.getText());
                }
                System.out.println();
            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
    }
}