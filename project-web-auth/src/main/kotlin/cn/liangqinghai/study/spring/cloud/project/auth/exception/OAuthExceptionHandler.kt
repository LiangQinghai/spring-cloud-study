package cn.liangqinghai.study.spring.cloud.project.auth.exception

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator
import java.lang.Exception

/**
 * oauth2异常处理
 */
class OAuthExceptionHandler : WebResponseExceptionTranslator<OAuth2Exception> {

    override fun translate(e: Exception?): ResponseEntity<OAuth2Exception> {

        log.error("OAuth2发生异常", e)

        return ResponseEntity.ok().body(e?.message?.let { OAuthException(it) })


    }

    companion object {
        private val log: Logger = LoggerFactory.getLogger(OAuthExceptionHandler::class.java)
    }

}