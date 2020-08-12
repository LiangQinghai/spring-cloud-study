package cn.liangqinghai.study.spring.cloud.project.common.security.feign

import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.SecurityConstants
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails

class OAuth2FeignRequestInterceptor : RequestInterceptor {
    override fun apply(template: RequestTemplate?) {

        val context = SecurityContextHolder.getContext()
        val authentication = context.authentication

        if (authentication != null && authentication.details is OAuth2AuthenticationDetails) {

            val details = authentication.details as OAuth2AuthenticationDetails
            template?.header(
                HttpHeaders.AUTHORIZATION,
                "${SecurityConstants.BEARER_TOKEN_TYPE} ${details.tokenValue}"
            )

        }

    }
}