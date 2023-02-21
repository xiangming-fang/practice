package com.xm.jy.job_cx.controller;

import com.xm.jy.test.spring.ioc.config.DayDayUpConfig;
import com.xm.jy.test.spring.ioc.config.GoodGoodStudyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: LitterHeart
 * @Description: 类的作用描述
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/4 18:41
 */
@Controller
public class LitterHeart {

    @Autowired
    private GoodGoodStudyConfig goodGoodStudyConfig;

    @Autowired
    private DayDayUpConfig dayDayUpConfig;

    @RequestMapping(value = "/getTextByIoc")
    @ResponseBody
    public String getTextByIoc(){
        return goodGoodStudyConfig.encourageSelf() + dayDayUpConfig.believeMe();
    }

    @RequestMapping(value = "/heart")
    public String getLitterHeart(){
        return "heart";
    }

    @RequestMapping(value = "/index")
    public String getIndex(){
        return "index";
    }


    @ResponseBody
    @RequestMapping(value = "/gm")
    public String getXXX(String a,Integer b,Float c,Double d,Character e,Byte f,Short g,Long h,boolean i){
//        System.out.println(a);
        return "干嘛";
    }
}
