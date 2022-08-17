package com.xm.jy.test.reflect;

import lombok.Data;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.reflect
 * @ClassName: BeanUtils
 * @Author: albert.fang
 * @Description: bean util
 * @Date: 2022/7/15 14:26
 */
public class BeanUtils {

    public static void main(String[] args) throws Exception {

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
        Dto dto = getDTO(form, new Dto());
        System.out.println(dto.toString());

    }

    public static <S,T> T getDTO(S s, T t){
        Class<?> sClass = s.getClass();
        Field[] declaredFields = sClass.getDeclaredFields();
        HashMap<String, Object> map = new HashMap<>(16);
        HashMap<String, Customized> customizedTypeMap = new HashMap<>(16);
        for (Field sField : declaredFields) {
            sField.setAccessible(true);
            try {
                map.put(sField.getName(),sField.get(s));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (sField.isAnnotationPresent(Customized.class)){
                Customized annotation = sField.getAnnotation(Customized.class);
                customizedTypeMap.put(sField.getName(),annotation);
            }
        }
        Class<?> tClass = t.getClass();
        Field[] tClassDeclaredFields = tClass.getDeclaredFields();
        for (Field tField : tClassDeclaredFields) {
            tField.setAccessible(true);
            Object o = map.get(tField.getName());
            // 目标字段是final 或者 源类型没有这个属性值
            if (Modifier.isFinal(tField.getModifiers()) || Objects.isNull(o)){
                continue;
            }
            if (Objects.nonNull(customizedTypeMap.get(tField.getName()))){
                if (o instanceof List){
                    List<Object> customizedTypeListObj = new ArrayList<>();
                    Type genericType = tField.getGenericType();
                    ((List)o).forEach( obj -> {
                        Object dto = null;
                        try {
                            dto = getDTO(obj, ((Class<?>)((ParameterizedType) genericType).getActualTypeArguments()[0]).getConstructor().newInstance());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        customizedTypeListObj.add(dto);
                    });
                    try {
                        tField.set(t,customizedTypeListObj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }else {
                    Object dto = null;
                    try {
                        dto = getDTO(o, tField.getType().getConstructor().newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        tField.set(t,dto);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }else {
                try {
                    tField.set(t,o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return t;
    }

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


    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Customized {
    }

}
