package com.xm.jy.test.jdk.reflect;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: xiangming.fang
 * @Date: 2023/5/18 18:32
 */
public class BeanUtilsTest {

    @Data
    static class Form{
        private int id;
        private String name;
        @Customized
        private SonForm son;
        @Customized
        private List<SonForm> sonList;
    }

    @Data
    static class SonForm{
        private int sonId;
        private String sonName;
        private String notSame;
        @Customized
        private GrandsonForm grandson;
    }

    @Data
    static class GrandsonForm{
        private int grandsonId;
        private String grandsonName;
    }

    @Data
    static class Dto{
        private int id;
        private String name;
        private int remain;
        private boolean flag;
        private double dou;
        private long l;
        private SonDto son;
        private List<SonDto> sonList;

        @Override
        public String toString(){
            return id + "\n" +
                    name + "\n" +
                    remain + "\n" +
                    flag + "\n" +
                    dou + "\n" +
                    l + "\n" +
                    son + "\n" +
                    sonList + "\n";
        }
    }

    @Data
    static class SonDto{
        private int sonId;
        private String sonName;
        private String extra;
        private GrandsonDto grandson;
    }

    @Data
    static class GrandsonDto{
        private int grandsonId;
        private String grandsonName;
    }

    public static void test(){
        Form form = new Form();
        form.setId(1);
        form.setName("方翔鸣");
        SonForm sonForm = new SonForm();
        sonForm.setSonId(11);
        sonForm.setSonName("方子钰");
        SonForm sonForm1 = new SonForm();
        sonForm1.setSonId(12);
        sonForm1.setSonName("方子璐");
        GrandsonForm grandsonForm = new GrandsonForm();
        grandsonForm.setGrandsonId(31);
        grandsonForm.setGrandsonName("孙子名字");
        sonForm.setGrandson(grandsonForm);
        form.setSonList(Stream.of(sonForm,sonForm1).collect(Collectors.toList()));
        form.setSon(sonForm);
        Dto dto = BeanUtils.getDTO(form, new Dto());
        System.out.println(dto.toString());
    }

    @Data
    static class Basic{
        public String basic;
    }

    @Data
    static class ExtendA extends Basic{
        private String extend;

        @Override
        public String toString() {
            return "ExtendA{" +
                    "basic='" + basic + '\'' +
                    ", extend='" + extend + '\'' +
                    '}';
        }
    }

    @Data
    static class ExtendB extends Basic{
        private String extend;

        @Override
        public String toString() {
            return "ExtendB{" +
                    "basic='" + basic + '\'' +
                    ", extend='" + extend + '\'' +
                    '}';
        }
    }

    @Data
    static class ExtendC {
        private String extend;
    }

    public static void convert(){
        ExtendA extendA = new ExtendA();
        extendA.basic = "basic basic basic";
        extendA.extend = "come 的 喂，abc";
        ExtendB extendB = BeanUtils.getDTO(extendA, new ExtendB());
        System.out.println(extendB.toString());
        ExtendC extendC = BeanUtils.getDTO(extendA, new ExtendC());
        System.out.println(extendC.toString());

        ExtendC cc = new ExtendC();
        cc.setExtend("parents not here");
        ExtendA come = BeanUtils.getDTO(cc, new ExtendA());
        System.out.println(come.toString());
    }

    public static void main(String[] args) throws Exception {
        convert();
    }
}
