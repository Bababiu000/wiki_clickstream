package com.example.wiki_clickstream.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.wiki_clickstream.mapper")
public class MyBatisPlusConfig {}

