package com.xm.jy.xhz.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fxm
 * @since 2023-06-29
 */
@TableName("myisam_test")
public class MyisamTest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyisamTest{" +
            "id=" + id +
            ", name=" + name +
        "}";
    }
}
