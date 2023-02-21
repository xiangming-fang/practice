package com.xm.jy.test.jdk.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.agent
 * @ClassName: MonkeyInvokerHandler
 * @Author: albert.fang
 * @Description: 猴子的动态代理
 * @Date: 2023/2/20 21:41
 */
public class MonkeyInvokerHandler implements InvocationHandler {

    // 真正的业务对象
    public Object target;

    public MonkeyInvokerHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理对象执行目标方法之前执行……");
        // 这里用的是target而不是proxy
        Object invoke = method.invoke(target, args);
        System.out.println(invoke.toString());
        System.out.println("被代理对象执行目标方法之后执行……");
        return invoke;
    }

    // 得到代理对象
    public Object getProxyObject(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }


}
