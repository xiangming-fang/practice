package com.xm.jy.test.designpattern.sevenstructualpattern.adapterpattern;

/**
 * @author: albert.fang
 * @date: 2021/1/14 11:21
 * @description: 适配器
 */
public class Adapter extends AbstractTarget {

    private EncryptAdaptee adaptee = new EncryptAdaptee();

    @Override
    public void encrypted() {
        System.out.println(adaptee.encrypt());
    }
}
