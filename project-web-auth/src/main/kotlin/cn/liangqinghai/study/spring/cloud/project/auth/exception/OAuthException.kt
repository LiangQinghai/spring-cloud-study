package cn.liangqinghai.study.spring.cloud.project.auth.exception

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception

@JsonSerialize(using = OAuthExceptionSerializer::class)
class OAuthException(msg: String) : OAuth2Exception(msg) {
}