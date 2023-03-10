package com.xm.jy.test.designpattern.elevenbehaviorpattern.templatemethod;

/**
 * @Author: xiangming.fang
 * @Description: 邮件发送
 * @Date: 2023/3/10 16:39
 */
public abstract class MailSend {

    // 模板方法
    // 组合各个部分，直接调用这个方法进行发送邮件
    public void  sendEmail(){
        System.out.printf("%s\n", getContext());
        System.out.printf("邮件内容：%s\n", getContext());
        System.out.printf("%s\n", getSyn());
    }

    public String getEmailTitle(){
        return "邮件标题";
    }

    public String getSyn(){
        return "得到发送结果";
    }

    // 声明一个抽象方法，用于这个抽象类的具体子类扩展实现
    // 场景：根据不同的目标客户类型，发送的模板消息是不一样的
    public abstract String getContext();
}
