package com.xm.jy.xhz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fxm
 * @since 2023-06-29
 */
public class T implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "c1", type = IdType.AUTO)
    private Integer c1;

    private String c2;

    private String c3;

    public Integer getc1() {
        return c1;
    }

    public void setc1(Integer c1) {
        this.c1 = c1;
    }
    public String getc2() {
        return c2;
    }

    public void setc2(String c2) {
        this.c2 = c2;
    }
    public String getc3() {
        return c3;
    }

    public void setc3(String c3) {
        this.c3 = c3;
    }

    @Override
    public String toString() {
        return "T{" +
            "c1=" + c1 +
            ", c2=" + c2 +
            ", c3=" + c3 +
        "}";
    }
}
