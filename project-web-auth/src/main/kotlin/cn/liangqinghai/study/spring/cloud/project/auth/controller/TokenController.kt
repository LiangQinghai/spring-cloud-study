package cn.liangqinghai.study.spring.cloud.project.auth.controller

import cn.liangqinghai.study.spring.cloud.project.api.RemoteLogService
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.SecurityConstants
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/token")
class TokenController {

    @Autowired
    val tokenStore: TokenStore? = null

    @Autowired
    val remoteLogService: RemoteLogService? = null

    @DeleteMapping("/logout")
    fun logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) authHeader: String): ResultDto<Any> {

        if (StringUtils.isEmpty(authHeader)) {
            return ResultDto.ok()
        }

        val tokenValue = authHeader.replace("Bearer", StringUtils.EMPTY).trim()
        val accessToken = tokenStore?.readAccessToken(tokenValue)

        if (accessToken == null || StringUtils.isEmpty(accessToken.value)) {
            return ResultDto.ok()
        }

        tokenStore?.removeAccessToken(accessToken)

        val refreshToken = accessToken.refreshToken
        tokenStore?.removeRefreshToken(refreshToken)
        val additionalInformation = accessToken.additionalInformation
        if (additionalInformation.containsKey(SecurityConstants.DETAILS_USERNAME)) {

            val username = additionalInformation.get(SecurityConstants.DETAILS_USERNAME) as String

            remoteLogService?.saveLoginInfo(username, "Logout", "退出成功")

        }

        return ResultDto.ok()

    }

}