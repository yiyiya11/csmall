package cn.tedu.csmall.order.webapi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// Mybatis框架要求扫描指定的包,才能使框架生效
@MapperScan("cn.tedu.csmall.order.webapi.mapper")
public class MyBatisConfiguration {
}
