package com.xm.jy.job_cx.controller;

import com.xm.jy.job_cx.exception.XmException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.job_51.controller
 * @ClassName: ExceptionController
 * @Author: albert.fang
 * @Description: 异常控制
 * @Date: 2022/12/30 14:36
 */
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/getMsg")
    public String getMsg(){
        throw new RuntimeException("say hello world fail");
    }

    @RequestMapping("/getXmMsg")
    public String getXmMsg(){
        throw new XmException("say Xm hello world fail");
    }
}
