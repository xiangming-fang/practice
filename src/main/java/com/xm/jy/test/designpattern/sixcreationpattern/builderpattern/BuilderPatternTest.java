package com.xm.jy.test.designpattern.sixcreationpattern.builderpattern;

/**
 * @author: albert.fang
 * @date: 2021/2/12 13:20
 * @description: 建造模式客户端测试
 */
public class BuilderPatternTest {
    public static void main(String[] args) {
        BuilderImpl builder = new BuilderImpl();
        builder.setProductId(1);
        builder.setProductName("1号产品");
        Product build = builder.build();
        System.out.println(build.toString());
        build.printProductId();
        System.out.println(build.getProductName());
    }
}
