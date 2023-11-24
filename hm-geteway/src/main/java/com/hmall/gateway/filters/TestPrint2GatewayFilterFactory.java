package com.hmall.gateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author sunshuxian
 * @date 2023-11-23 08:33
 */
@Component
public class TestPrint2GatewayFilterFactory extends AbstractGatewayFilterFactory<TestPrint2GatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String a = config.getA();
                String b = config.getB();
                String c = config.getC();

                System.out.printf("a=%s,b=%s,c=%s", a, b,c);
                return chain.filter(exchange);
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a","b","c");
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Data
    @Validated
    public static class Config {
        private String a;
        private String b;
        private String c;

    }
}
