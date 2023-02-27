package com.xm.jy.test.designpattern.sevenstructualpattern.decoratorpattern.decorators;

import com.xm.jy.test.designpattern.sevenstructualpattern.decoratorpattern.Cache;

/**
 * @Author: albert.fang
 * @Description: lru 缓存
 * @Date: 2023/2/27 16:38
 */
public class LruCache implements Cache {

    // 被装饰类
    private Cache cache;

    public LruCache(Cache cache){
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
