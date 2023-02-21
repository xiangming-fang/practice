package com.xm.jy.test.tools.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @ClassName: XMLParse
 * @Description: xml document parsing
 * @Author: Albert
 * @CreateDate: 2020/1/9 9:40
 */
public class XMLParse {
    public static void main(String[] args) {
        try {
            // 创建解析器
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 把xml文件加载进内存
            try {
                Document document = db.parse("E:\\IdeaProjects\\timer\\src\\main\\java\\com\\cx\\timer\\xml\\Three.xml");
                // 获取文件中的根节点
                NodeList root = document.getChildNodes();
                for (int i = 0; i < root.getLength(); i++) {
                    Node childNodes = root.item(i);
                    // 通过根节点获取子节点
                    NodeList childNodes1 = childNodes.getChildNodes();
                    for (int j = 0; j < childNodes1.getLength(); j++) {
                        Node node = childNodes1.item(j);
                        // 获取子节点的子节点
                        NodeList value = node.getChildNodes();
                        // 遍历各个节点的属性值
                        for (int k = 0; k < value.getLength(); k++) {
                            if (value.item(k).getNodeName() != "#text"){
                                System.out.println(value.item(k).getNodeName()+":"+value.item(k).getTextContent());
                            }
                        }
                        System.out.println();
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
