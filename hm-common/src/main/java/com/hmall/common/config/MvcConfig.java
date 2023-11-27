package com.hmall.common.config;

import com.hmall.common.interceptor.UserInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author sunshuxian
 * @date 2023-11-27 09:05
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 默认拦截所有
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
