package cn.liangqinghai.study.spring.cloud.project.gateway.config;

import cn.liangqinghai.study.spring.cloud.project.gateway.handler.SentinelFallbackHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author LiangQinghai
 * @title GatewayConfig
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/11 15:19
 */
@Configuration
public class GatewayConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelFallbackHandler() {
        return new SentinelFallbackHandler();
    }


    @Bean
    @Order(-1)
    public GlobalFilter globalFilter() {
        return new SentinelGatewayFilter();
    }

}
