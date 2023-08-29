package com.iot.gateway.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.iot.common.domain.JwtTokenDto;
import com.iot.common.domain.R;
import com.iot.common.utils.JWTUtils;
import com.iot.common.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-22 14:41
 */
@Component
@Slf4j
public class GatewayAuthFilter implements GlobalFilter, Ordered {

    private static final String WHITE_PROPER = "/security-whitelist.properties";

    private static List<String> whiteList;

    static {
        try (InputStream resourceAsStream = GatewayAuthFilter.class.getResourceAsStream(WHITE_PROPER)) {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            Set<String> propNames = properties.stringPropertyNames();
            whiteList = new ArrayList<>(propNames);
        } catch (Exception ex) {
            log.error("加载WHITE_PROPER错误-------");
            throw new RuntimeException("加载WHITE_PROPER错误", ex);
        }

    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        // 请求的url
        ServerHttpRequest request = exchange.getRequest();
        String requestUrl = request.getPath().value();
        for (String white : whiteList) {
            if (pathMatcher.match(white, requestUrl)) {
                return chain.filter(exchange);
            }
        }
        // 校验token是否存在
        String token = getToken(exchange);
        if (StringUtils.isBlank(token)) {
            return buildReturnMono("没有认证!", exchange);
        }

        try {
            // 校验token是否过期
            JwtTokenDto jwtTokenDto = JWTUtils.parseToken(token);
            if (jwtTokenDto.isExpiration()) {
                return buildReturnMono("令牌已过期！", exchange);
            }

        } catch (Exception ex) {
            return buildReturnMono("令牌解析错误!", exchange);
        }

        return Mono.empty();
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> buildReturnMono(String errorMsg, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        String jsonString = JSON.toJSONString(RUtils.error(errorMsg));
        byte[] bits = jsonString.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * 获取token
     */
    private String getToken(ServerWebExchange exchange) {
        String tokenStr = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isBlank(tokenStr)) {
            return null;
        }
        String token = tokenStr.split(" ")[1];
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token;
    }
}
