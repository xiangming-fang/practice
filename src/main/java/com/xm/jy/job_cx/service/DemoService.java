package com.xm.jy.job_cx.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @ClassName: DemoService
 * @Description: 定时任务例子
 * @Author: 方翔鸣
 * @CreateDate: 2019/11/4 14:08
 */
//@Controller
public class DemoService {

    @Scheduled(cron = "0/3 * * * * ?")
    public void demoSchedule() {
        System.out.println("hello world,XXOO");
    }

    @Scheduled(cron = "0/3 * * * * ?")
    public void demoScheduleOne(){
        System.out.println("hello;this is single or not");
    }
}
