package com.xm.jy.test.designpattern.sevenstructualpattern.decoratorpattern;

/**
 * @Author: albert.fang
 * @Description: 缓存
 * @Date: 2023/2/27 16:36
 */
public interface Cache {

    int getSize();

    Object getObject();

    void putObject();
}
