package com.xm.jy.job_51.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author: albert.fang
 * @date: 2020/4/28 11:40
 * @description: cookie工具类（cookie是保存在客户端的）
 */
public class CookieUtil {

    /**
     * 往HttpServletResponse里添加cookie
     * @param response
     * @param name 指定的cookie名
     * @param value 指定的cookie值
     * @param maxAge 设置的cookie存活时间单位是s；
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge){
        Cookie cookie = new Cookie(name,value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    /**
     * 从HttpServletRequest里获取到指定的cookie名的cookie值
     * @param request
     * @param name 指定的cookie名
     * @return
     */
    public static Cookie get(HttpServletRequest request,String name){
        HashMap<String,Cookie> cookieMap =  cookieToMap(request);
        if (cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }
        return null;
    }

    private static HashMap<String, Cookie> cookieToMap(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        HashMap<String,Cookie> cookieMap = new HashMap<>();
        if (cookies != null){
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
