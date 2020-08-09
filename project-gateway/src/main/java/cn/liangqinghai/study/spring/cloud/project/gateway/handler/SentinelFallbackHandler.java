package cn.liangqinghai.study.spring.cloud.project.gateway.handler;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 限流
 *
 * @author Liang
 */
public class SentinelFallbackHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        return null;
    }
}
