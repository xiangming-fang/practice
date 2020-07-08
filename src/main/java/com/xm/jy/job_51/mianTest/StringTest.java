package com.xm.jy.job_51.mianTest;

import java.util.Scanner;

/**
 * @author: albert.fang
 * @date: 2020/7/7 17:22
 * @description: �ַ���ƥ��ʵ��
 */
public class StringTest {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("�����븸����");
            String source = scanner.next();
            System.out.println("�������Ӵ���");
            String target = scanner.next();
            System.out.println("�����Ƿ�������Ӵ���");
            System.out.println(containString(source,target));
            System.out.println("------------");
        }
    }

    private static boolean containString(String source,String target) throws Exception {
        if (source == null || target == null){
            throw new Exception("Դ�ַ�����Ŀ���ַ�����������Ϊnull");
        }
        if (source.length() < target.length()){
            throw new Exception("�Ӵ����ȱȸ�����");
        }
        if (target.equals("")){
            return true;
        }
        char[] sourceChars = source.toCharArray();
        char[] targetChars = target.toCharArray();
        // ������ȵ��ַ�����
        int targetLength = 0;
        for (int i = 0; i < sourceChars.length; i++) {
            // �����������Ƿ����Ӵ��ĵ�һ���ַ�
            if (sourceChars[i] == targetChars[0]){
                // ����Ӵ���ֻ��һ���ַ�
                if (targetChars.length == 1){
                    return true;
                }
                // ����еĻ����ȶԸ����������ַ����Ⱥ��Ӵ��������Ƚ�
                if (sourceChars.length - i < targetChars.length){
                    return false;
                }
                // ������ȷ��ϣ���ô���ζԺ����Ӵ��ַ����бȽ�
                for (int j = 0; j < targetChars.length; j++) {
                    if (targetChars[j] == sourceChars[i+j]){
                        targetLength++;
                    }
                    // ������������ϣ���ô��targetLength��ֵ0,Ϊ�����´θ��õ��ж�
                    else {
                        targetLength = 0;
                    }
                }
                // �ҵ�֮��������
                if (targetLength == targetChars.length){
                    return true;
                }

            }
        }
        return false;
    }
}
