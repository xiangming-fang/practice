package com.xm.jy.test.designpattern.elevenbehaviorpattern.templatemethod;

/**
 * @Author: xiangming.fang
 * @Description: vip用户
 * @Date: 2023/3/10 16:46
 */
public class VipMail extends MailSend{
    @Override
    public String getContext() {
        return "尊贵的vip用户，您好";
    }
}
