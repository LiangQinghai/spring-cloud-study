package cn.liangqinghai.study.spring.cloud.project.auth.config

import cn.liangqinghai.study.spring.cloud.project.auth.exception.OAuthExceptionHandler
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.CacheConstants
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.SecurityConstants
import cn.liangqinghai.study.spring.cloud.project.common.security.domain.LoginUser
import cn.liangqinghai.study.spring.cloud.project.common.security.service.RedisClientDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
open class AuthConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    val authenticationManager: AuthenticationManager? = null

    @Autowired
    val dataSource: DataSource? = null

    @Autowired
    val redisConnectionFactory: RedisConnectionFactory? = null

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    val userDetails: UserDetailsService? = null

    @Autowired
    val tokenEnhancer: TokenEnhancer? = null

    @Bean
    open fun tokenStore(): TokenStore {

        val tokenStore = RedisTokenStore(redisConnectionFactory)
        tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS)
        return tokenStore

    }

    @Bean
    open fun tokenEnhancer(): TokenEnhancer {

        return TokenEnhancer { accessToken, authentication ->
            if (authentication.userAuthentication != null) {

                val info: MutableMap<String, Any?> = LinkedHashMap()
                val loginUser = authentication.userAuthentication.principal as LoginUser
                info[SecurityConstants.DETAILS_USER_ID] = loginUser.userId
                info[SecurityConstants.DETAILS_USERNAME] = loginUser.username
                (accessToken as DefaultOAuth2AccessToken).additionalInformation = info

            }

            accessToken
        }

    }

    /**
     * 定义授权和token
     */
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {

        endpoints?.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST) // 请求方式
            ?.tokenStore(tokenStore()) // 指定token存储方式
            ?.tokenEnhancer(tokenEnhancer) // 生成令牌方式
            ?.userDetailsService(userDetails) // 用户账号密码
            ?.authenticationManager(authenticationManager) // 认证管理器
            ?.reuseRefreshTokens(false) // 是否使用refresh——token
            ?.exceptionTranslator(OAuthExceptionHandler()) // 异常处理

    }

    override fun configure(security: AuthorizationServerSecurityConfigurer?) {

        security?.allowFormAuthenticationForClients()?.checkTokenAccess("permitAll")

    }

    /**
     * redis client detail
     *
     * @return
     */
    private fun clientDetailsService(): RedisClientDetailsService? {
        return dataSource?.let { RedisClientDetailsService(it) }
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients?.withClientDetails(clientDetailsService())
    }
}