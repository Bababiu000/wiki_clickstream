package com.example.wiki_clickstream.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/wiki_clickstream?serverTimezone=GMT%2B8", "root", "colt1911")
                .globalConfig(builder -> {
                    builder.fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\Code\\wiki_clickstream\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.wiki_clickstream") // 设置父包名
                            .controller("controller")
                            .entity("entity")
                            .service("service")
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Code\\wiki_clickstream\\src\\main\\resources"));// 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("clickstream"); // 设置需要生成的表名
                })
                .execute();
    }
}
