package com.xm.jy.test.jdk.reflect;

/**
 * @auther 方翔鸣
 * @date 2020/3/4 17:52
 * 供测试反射用的实体
 */
public class ReflectEntity {
    private Integer age;
    private String name;
    public String gender;

    public ReflectEntity(String name,Integer age){
        this.age = age;
        this.name = name;
    }

    private ReflectEntity(){

    }


    public Integer getNumber() {
        return age;
    }

    public void setNumber(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ReflectEntity{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
