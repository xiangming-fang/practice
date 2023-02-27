package com.xm.jy.test.designpattern.sixcreationpattern.builderpattern;

/**
 * @author: albert.fang
 * @date: 2023/2/12 13:15
 * @description: 具体建造者
 */
public class BuilderImpl implements Builder {

    private ProductImpl product = new ProductImpl();

    @Override
    public void setProductName(String productName) {
        product.setProductName(productName);
    }

    @Override
    public void setProductId(int productId) {
        product.setProductId(productId);
    }

    @Override
    public Product build() {
        return product;
    }
}
