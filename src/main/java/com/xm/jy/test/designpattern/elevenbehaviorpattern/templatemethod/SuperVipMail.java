package com.xm.jy.test.designpattern.elevenbehaviorpattern.templatemethod;

/**
 * @Author: xiangming.fang
 * @Description: 尊贵的超级vip用户邮件模板类型
 * @Date: 2023/3/10 16:44
 */
public class SuperVipMail extends MailSend{
    @Override
    public String getContext() {
        return "尊贵的超级vip用户，您好";
    }
}
