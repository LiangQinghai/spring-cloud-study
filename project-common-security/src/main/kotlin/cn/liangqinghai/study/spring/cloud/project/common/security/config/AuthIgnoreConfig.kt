package cn.liangqinghai.study.spring.cloud.project.common.security.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@ConfigurationProperties(prefix = "security.oauth2.ignore")
open class AuthIgnoreConfig {

    var urls: ArrayList<String?>? = null

}