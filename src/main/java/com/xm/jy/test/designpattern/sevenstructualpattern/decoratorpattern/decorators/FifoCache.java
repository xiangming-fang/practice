package com.xm.jy.test.designpattern.sevenstructualpattern.decoratorpattern.decorators;

import com.xm.jy.test.designpattern.sevenstructualpattern.decoratorpattern.Cache;

/**
 * @Author: albert.fang
 * @Description: fifo 缓存
 * @Date: 2023/2/27 16:40
 */
public class FifoCache implements Cache {

    private Cache cache;

    public FifoCache(Cache cache){
        this.cache = cache;
    }
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
