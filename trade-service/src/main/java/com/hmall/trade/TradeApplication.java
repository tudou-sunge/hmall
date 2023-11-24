package com.hmall.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.hmall.api.config.DefaultFeignConfig;
/**
 * @author sunshuxian
 * @date 2023-11-20 09:43
 */
@MapperScan("com.hmall.trade.mapper")
@SpringBootApplication
@EnableFeignClients(value = "com.hmall.api.client", defaultConfiguration = DefaultFeignConfig.class)
public class TradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeApplication.class, args);
    }

}
