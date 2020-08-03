package cn.liangqinghai.study.spring.cloud.gateway.filter;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;

/**
 * @author LiangQinghai
 * @title GlobalFilter
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 20:05
 */
@Component
@Slf4j
public class AccessGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info(AccessGlobalFilter.class.getCanonicalName(), System.currentTimeMillis());
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        RequestPath path = request.getPath();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        HttpHeaders headers = request.getHeaders();

        log.info("queryParams: {}", JSONUtil.toJsonPrettyStr(queryParams));
        log.info("cookies: {}", JSONUtil.toJsonPrettyStr(cookies));
        log.info("path: {}", JSONUtil.toJsonPrettyStr(path));
        log.info("remoteAddress: {}", JSONUtil.toJsonPrettyStr(remoteAddress));
        log.info("headers: {}", JSONUtil.toJsonPrettyStr(headers));
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
