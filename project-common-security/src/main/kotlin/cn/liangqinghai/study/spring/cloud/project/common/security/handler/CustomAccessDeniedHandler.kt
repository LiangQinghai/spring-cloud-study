package cn.liangqinghai.study.spring.cloud.project.common.security.handler

import cn.hutool.http.ContentType
import cn.hutool.http.HttpStatus
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import com.alibaba.fastjson.JSON
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAccessDeniedHandler : OAuth2AccessDeniedHandler() {

    companion object {

        private val log = LoggerFactory.getLogger(CustomAccessDeniedHandler::class.java)

    }

    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AccessDeniedException?
    ) {

        log.error("权限不足，请联系管理员 {}", request?.requestURI)

        response?.status = HttpStatus.HTTP_OK
        response?.contentType = ContentType.JSON.value
        response?.characterEncoding = StandardCharsets.UTF_8.displayName()
        response?.writer?.print(
            JSON.toJSONString(
                ResultDto.fail<Any>(
                    HttpStatus.HTTP_FORBIDDEN,
                    authException?.message
                )
            )
        )

    }
}