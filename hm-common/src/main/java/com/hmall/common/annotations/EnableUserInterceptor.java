package com.hmall.common.annotations;

import com.hmall.common.config.MvcConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 因为MvcConfig使用了@Configuration注解，但是想要在其他项目中被自动装配有两种方式
 * 1. 这种方式，在需要装配MvcConfig的类上使用EnableUserInterceptorh注解,即可实现自动装配
 * 2. 在META-INF/spring.factories中配置需要自动装配的类
 * */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(MvcConfig.class)
public @interface EnableUserInterceptor {
}
