package cn.liangqinghai.study.spring.cloud.project.common.security.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.RemoteTokenServices
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Configuration
@EnableResourceServer
open class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private val resourceServerProperties: ResourceServerProperties? = null

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private val oAuth2ClientProperties: OAuth2ClientProperties? = null

    @Bean
    open fun authIgnoreConfig(): AuthIgnoreConfig {
        return AuthIgnoreConfig()
    }

    @Bean
    @LoadBalanced
    open fun restTemplate(): RestTemplate {

        val restTemplate = RestTemplate()

        restTemplate.errorHandler = DefaultResponseErrorHandler()

        return restTemplate

    }

    @Bean
    open fun tokenService(): ResourceServerTokenServices {

        val remoteTokenServices = RemoteTokenServices()
        val accessTokenConverter = DefaultAccessTokenConverter()
        val commonUserConverter = CommonUserConverter()
        accessTokenConverter.setUserTokenConverter(commonUserConverter)
        remoteTokenServices.setCheckTokenEndpointUrl(resourceServerProperties?.tokenInfoUri)
        remoteTokenServices.setClientId(oAuth2ClientProperties?.clientId)
        remoteTokenServices.setClientSecret(oAuth2ClientProperties?.clientSecret)
        remoteTokenServices.setRestTemplate(restTemplate())
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter)

        return remoteTokenServices

    }

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        super.configure(resources)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity?) {

        super.configure(http)
        http?.csrf()?.disable()
        val authorizeRequests = http?.authorizeRequests()

        authIgnoreConfig().urls?.forEach { authorizeRequests?.antMatchers(it)?.permitAll() }

        authorizeRequests?.anyRequest()?.authenticated()

    }
}