package com.xm.jy.job_cx.handler;

import com.xm.jy.job_cx.exception.XmException;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.job_cx.handler
 * @ClassName: ExceptionHandler
 * @Author: albert.fang
 * @Description:
 * @Date: 2022/12/30 14:42
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandler {

//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
//    @ResponseBody
//    public String handlerException(Exception e){
//        log.error(e.toString(),e);
//        return "exception handler hello world";
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {XmException.class})
    @ResponseBody
    public String handlerException(XmException e){
        log.error(e.toString() + " 搞毛啊 ",e);
        return "Xm exception handler hello world";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseBody
    public String handleException(HttpServletRequest request, Throwable e) {
        log.error(e.toString() + " 没指定异常 ",e);
        return "没有指定异常";
    }
}
