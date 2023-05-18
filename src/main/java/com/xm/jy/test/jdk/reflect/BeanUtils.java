package com.xm.jy.test.jdk.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ProjectName: practice
 * @Package: com.xm.jy.test.reflect
 * @ClassName: BeanUtils
 * @Author: albert.fang
 * @Description: bean util
 * @Date: 2022/7/15 14:26
 */
public class BeanUtils {

    public static <S,T> T getDTO(S s, T t){
        Class<?> sClass = s.getClass();
        List<Field> declaredFields = Arrays.stream(sClass.getDeclaredFields()).collect(Collectors.toList());
        while ((sClass = sClass.getSuperclass()) != Object.class){
            declaredFields.addAll(Arrays.stream(sClass.getDeclaredFields()).collect(Collectors.toList()));
        }
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
        List<Field> tClassDeclaredFields = Arrays.stream(tClass.getDeclaredFields()).collect(Collectors.toList());
        while ((tClass = tClass.getSuperclass()) != Object.class){
            tClassDeclaredFields.addAll(Arrays.stream(tClass.getDeclaredFields()).collect(Collectors.toList()));
        }
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

}
