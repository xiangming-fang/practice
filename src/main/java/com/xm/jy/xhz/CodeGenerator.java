package com.xm.jy.xhz;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: xiangming.fang
 * @Date: 2023/6/29 14:17
 */
public class CodeGenerator {

    public static void main(String[] args) throws IOException {
        String url = "jdbc:mysql://localhost/xiaohaizi?characterEncoding=utf-8&useSSL=false";
        String username = "root";
        String password = "123456";

        HashMap<OutputFile, String> map = new HashMap<>();
        map.put(OutputFile.mapperXml, "src/main/resources/mybatis/xiaohaizi");
        map.put(OutputFile.mapper,"D:\\study\\practice\\src\\main\\java\\com\\xm\\jy\\xhz\\mapper");
        map.put(OutputFile.entity,"D:\\study\\practice\\src\\main\\java\\com\\xm\\jy\\xhz\\pojo");
        map.put(OutputFile.service,"D:\\study\\practice\\src\\main\\java\\com\\xm\\jy\\xhz\\service");
        map.put(OutputFile.serviceImpl,"D:\\study\\practice\\src\\main\\java\\com\\xm\\jy\\xhz\\service\\impl");
        map.put(OutputFile.controller,"D:\\study\\practice\\src\\main\\java\\com\\xm\\jy\\xhz\\controller");

        FastAutoGenerator.create(url,username,password)
                .globalConfig(builder -> {
                    builder.author("fxm") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride(); // 覆盖已生成文件
//                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.xm.jy.xhz")// 设置父包名
                            .entity("pojo")
                            .pathInfo(map) // 设置mapperXml生成路径
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude("single_table2"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
