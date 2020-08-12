package cn.liangqinghai.study.spring.cloud.project.auth.handler

import cn.liangqinghai.study.spring.cloud.project.api.RemoteLogService
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.Constants
import cn.liangqinghai.study.spring.cloud.project.common.security.domain.LoginUser
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AuthenticationSuccessEventHandler : ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    val remoteLogService: RemoteLogService? = null

    override fun onApplicationEvent(event: AuthenticationSuccessEvent) {

        val authentication = event.source as Authentication

        if (StringUtils.isNoneEmpty(authentication.authorities.toString())
            && authentication.principal is LoginUser
        ) {

            val loginUser = authentication.principal as LoginUser
            val username = loginUser.username

            remoteLogService?.saveLoginInfo(username, Constants.LOGIN_SUCCESS, "登陆成功")

        }

    }

}