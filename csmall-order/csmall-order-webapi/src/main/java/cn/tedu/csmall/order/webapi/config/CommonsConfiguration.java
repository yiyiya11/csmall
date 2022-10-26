package cn.tedu.csmall.order.webapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// 只有添加了@Configuration注解的类才能配置当前Spring的环境
@Configuration
// 要扫描commons模块中的统一异常处理类所在的路径,否则异常处理功能不会生效
@ComponentScan("cn.tedu.csmall.commons.exception")
public class CommonsConfiguration {
}
