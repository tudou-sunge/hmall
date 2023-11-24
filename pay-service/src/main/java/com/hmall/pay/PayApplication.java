package com.hmall.pay;


import com.hmall.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sunshuxian
 * @date 2023-11-20 09:43
 */
@MapperScan("com.hmall.pay.mapper")
@SpringBootApplication
@EnableFeignClients(value = "com.hmall.api.client", defaultConfiguration = DefaultFeignConfig.class)
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }

}
