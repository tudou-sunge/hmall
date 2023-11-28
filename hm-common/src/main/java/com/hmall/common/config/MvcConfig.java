package com.hmall.common.config;

import com.hmall.common.interceptor.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author sunshuxian
 * @date 2023-11-27 09:05
 */
@Configuration
@ConditionalOnClass(DispatcherServlet.class)  // 当有DispatcherServlet时才生效，虽然引用了common包，但是没有这个DispatcherServlet类时，这个不会自动装配
public class MvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
        super.addInterceptors(registry);
    }
}
