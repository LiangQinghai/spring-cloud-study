package cn.liangqinghai.study.spring.cloud.project.gateway.handler;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 限流
 *
 * @author Liang
 */
public class SentinelFallbackHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        ServerHttpResponse response = serverWebExchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(throwable);
        }

        if (!BlockException.isBlockException(throwable)) {
            return Mono.error(throwable);
        }

        return handleBlockedRequest(serverWebExchange, throwable)
                .flatMap(serverResponse -> {

                    response.getHeaders().add(Header.CONTENT_TYPE.getValue(), ContentType.JSON + ";charset=UTF-8");

                    byte[] datas = "{\"status\":429,\"message\":\"请求超过最大数，请稍后再试\"}".getBytes(StandardCharsets.UTF_8);

                    DataBuffer buffer = response.bufferFactory().wrap(datas);

                    return response.writeWith(Mono.just(buffer));

                });

    }

    private Mono<ServerResponse> handleBlockedRequest(ServerWebExchange exchange, Throwable ex) {

        return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, ex);

    }

}
