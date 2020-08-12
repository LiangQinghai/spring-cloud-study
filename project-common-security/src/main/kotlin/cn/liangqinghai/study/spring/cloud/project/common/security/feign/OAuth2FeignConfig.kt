package cn.liangqinghai.study.spring.cloud.project.common.security.feign

import feign.RequestInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OAuth2FeignConfig {

    @Bean
    open fun requestInterceptor(): RequestInterceptor {

        return OAuth2FeignRequestInterceptor()

    }

}