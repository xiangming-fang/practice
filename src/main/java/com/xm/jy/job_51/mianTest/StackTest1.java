package com.xm.jy.job_51.mianTest;

import java.util.LinkedList;

/**
 * @author: albert.fang
 * @date: 2020/7/7 14:50
 * @description: ջ����ϰ���ж��ַ�����()[]���Ƿ�Ϸ�
 * �Ϸ�������Ӧ����������ߣ���˳��ƥ�������ţ��Ϸ�eg����[]()����[(())]
 *
 * ˼·������ֻ������[]()���ַ����ַ��������������������ѹջ����
 * �������������ѹ��ջ����Ԫ�ؽ��г�ջ�����������жϳ�ջ�������źͱ����õ����������Ƿ�Ϊһ�ԣ�
 * �����һ�������������һ����ֱ�����н������жϸ��ַ���Ϊ�Ƿ��ַ�����
 *
 * �ѵ㣺����ж�[]������������Ϊ��ȷ���������ţ�
 *
 * ���˼·����װһ���������������ֿ���ƥ��Ϊtrue
 */
public class StackTest1 {

    public static void main(String[] args) throws Exception {
        String str = "{[](})}";
        LinkedList linkedList = new LinkedList();
        for (char c : str.toCharArray()) {
            if (c == '{' || c == '[' || c == '('){
                linkedList.push(c);
            }
            else {
                if (!compareBracket(c, (Character) linkedList.pop())){
                    throw new Exception("�Ƿ��ַ�");
                }
            }
        }
        if (linkedList.size() == 0){
            System.out.println("�Ϸ��ַ�");
        }
    }

    /**
     * �Ƚ� a,b���������Ƿ�Ϊһ��
     * @param a
     * @param b
     * @return true a,bһ�����ţ�false��һ��
     */
    private static boolean compareBracket(char a,char b){
        return (a == '(' && b == ')') || (a == ')' && b == '(') ||  (a == '{' && b == '}') || (a == '}' && b == '{') || (a == '[' && b == ']') || (a == ']' && b == '[');
    }
}
