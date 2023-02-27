package com.xm.jy.test.designpattern.sixcreationpattern.builderpattern;

import lombok.Data;

/**
 * @Author: albert.fang
 * @Description: 具体产品
 * @Date: 2023/2/27 17:27
 */
@Data
public class ProductImpl implements Product{

    private String productName;

    private int productId;

    @Override
    public void printProductName() {
        System.out.println(productName);
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public void printProductId() {
        System.out.println(productId);
    }

    @Override
    public int getProductId() {
        return productId;
    }

}
