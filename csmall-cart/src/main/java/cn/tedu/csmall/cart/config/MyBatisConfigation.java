package cn.tedu.csmall.cart.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//Mybatis需要扫描相应的报
@MapperScan("cn.tedu.csmall.cart.mapper")
public class MyBatisConfigation {
}
