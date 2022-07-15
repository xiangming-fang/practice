package com.xm.jy.test.reflect;

import com.xm.jy.test.util.JacksonUtil;
import lombok.Data;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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
        form.setSonList(Stream.of(sonForm,sonForm1).collect(Collectors.toList()));
        form.setSon(sonForm);
        Dto dto = getDTO(form, new Dto());
        System.out.println(dto.toString());

    }

    static <S,T> T getDTO(S s,T t) throws Exception {
        Class<?> sClass = s.getClass();
        Field[] declaredFields = sClass.getDeclaredFields();
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Customized> customizedTypeMap = new HashMap<>();
        for (Field sField : declaredFields) {
            sField.setAccessible(true);
            map.put(sField.getName(),sField.get(s));
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
            if (tField.isAnnotationPresent(Customized.class)){
                Customized customized = customizedTypeMap.get(tField.getName());
                if (Objects.isNull(customized)){
                    throw new RuntimeException(tField.getName() + "source 没有自定义类型属性");
                }
                if (customized.isList()){
                    List<Object> customizedTypeListObj = new ArrayList<>();
                    ((List)o).forEach( obj -> {
                        Object dto = null;
                        try {
                            dto = getDTO(obj, customized.targetType().newInstance());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        customizedTypeListObj.add(dto);
                    });
                    tField.set(t,customizedTypeListObj);
                }else {
                    Object dto = null;
                    try {
                        dto = getDTO(o, customized.targetType().newInstance());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tField.set(t,dto);
                }
            }else {
                tField.set(t,o);
            }
        }
        return t;
    }

    @Data
    static class Form{
        private int id;
        private String name;
        @Customized(sourceType = SonForm.class,targetType = SonDto.class)
        private SonForm son;
        @Customized(sourceType = SonForm.class,targetType = SonDto.class,isList = true)
        private List<SonForm> sonList;
    }

    @Data
    static class Dto{
        private int id;
        private String name;
        private int remain;
        private boolean flag;
        private double dou;
        private long l;
        @Customized
        private SonDto son;
        @Customized
        private List<SonDto> sonList;
    }

    @Data
    static class SonForm{
        private int sonId;
        private String sonName;
        private String notSame;
    }

    @Data
    static class SonDto{
        private int sonId;
        private String sonName;
        private String extra;
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface Customized {

        /**
         * 源类型
         */
        Class<?> sourceType() default Object.class;

        /**
         * 目标类型
         */
        Class<?> targetType() default Object.class;

        /**
         * 自定义类型是但对象还是集合对象，默认单对象
         */
        boolean isList() default false;

    }

    // todo 就担心source的多，target的少

}
