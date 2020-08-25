package cn.liangqinghai.study.spring.cloud.project.auth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Order(99)
@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Bean
    open fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity?) {

        http?.authorizeRequests()
                ?.antMatchers("/actuator/**", "/oauth/*", "/token/**")
                ?.permitAll()
                ?.anyRequest()
                ?.authenticated()
                ?.and()
                ?.csrf()
                ?.disable()

    }
}