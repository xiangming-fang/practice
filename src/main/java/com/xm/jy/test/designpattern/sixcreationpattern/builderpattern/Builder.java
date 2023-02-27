package com.xm.jy.test.designpattern.sixcreationpattern.builderpattern;

/**
 * @author: albert.fang
 * @date: 2023/2/12 13:07
 * @description: 建造模式抽象建造者
 */
public interface Builder {

    void setProductName(String productName);

    void setProductId(int productId);

    Product build();

}
