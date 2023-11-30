package com.hmall.api.config;

import com.hmall.api.interceptor.FeignRequestInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author sunshuxian
 * @date 2023-11-20 08:44
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
