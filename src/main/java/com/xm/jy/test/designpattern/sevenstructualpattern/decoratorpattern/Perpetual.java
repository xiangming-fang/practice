package com.xm.jy.test.designpattern.sevenstructualpattern.decoratorpattern;

/**
 * @Author: albert.fang
 * @Description: 被装饰类
 * @Date: 2023/2/27 16:37
 */
public class Perpetual implements Cache{
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public Object getObject() {
        return null;
    }

    @Override
    public void putObject() {

    }
}
