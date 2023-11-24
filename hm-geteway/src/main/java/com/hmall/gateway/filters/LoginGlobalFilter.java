package com.hmall.gateway.filters;

import cn.hutool.core.text.AntPathMatcher;
import com.hmall.common.exception.UnauthorizedException;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.List;

/**
 * @author sunshuxian
 * @date 2023-11-24 08:39
 */

@Component
@RequiredArgsConstructor
public class LoginGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final JwtTool jwtTool;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取request对象
        ServerHttpRequest request = exchange.getRequest();
        // 2.判断当前请求是否需要拦截
        if (isAllowPath(request)) {
            return chain.filter(exchange);
        }
        // 3.是否含有token
        List<String> authorization = request.getHeaders().get("authorization");
        Optional<List<String>> authorizationList = Optional.ofNullable(authorization);
        if (authorizationList.isEmpty() || authorizationList.get().isEmpty()) {
            throw new UnauthorizedException("未登陆, 无法启动");
        }
        // 4. 需要拦截 解析token
        String token = authorizationList.get().get(0);
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(401);
            return response.setComplete();
        }

        System.out.printf("UserId:{}", userId);
        // 5. 放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private boolean isAllowPath(ServerHttpRequest request) {
        boolean flag = false;
        // 获取当前路径
        String method = request.getMethodValue();
        String path = request.getPath().toString();
        // 需要放行的路径
        for (String excludePath : authProperties.getExcludePaths()) {
            if (pathMatcher.match(excludePath, method + ":" + path)) {
                return true;
            }
        }
        return flag;
    }
}
