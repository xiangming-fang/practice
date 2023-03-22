package com.xm.jy.test.designpattern.elevenbehaviorpattern.templatemethod;

/**
 * @Author: xiangming.fang
 * @Description: 模板方法设计模式测试类
 * @Date: 2023/3/10 16:46
 */
public class TemplateMethodTest {
    public static void main(String[] args) {
        SuperVipMail superVipMail = new SuperVipMail();
        superVipMail.sendEmail();
        VipMail vipMail = new VipMail();
        vipMail.sendEmail();
    }
}
