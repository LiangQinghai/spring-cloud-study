package cn.liangqinghai.study.spring.cloud.project.common.security.annos

import cn.liangqinghai.study.spring.cloud.project.common.security.config.ApplicationConfig
import cn.liangqinghai.study.spring.cloud.project.common.security.config.SecurityImportBeanDefinitionRegistrar
import cn.liangqinghai.study.spring.cloud.project.common.security.feign.OAuth2FeignConfig
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableAsync
import java.lang.annotation.Inherited

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Inherited
@EnableAspectJAutoProxy(exposeProxy = true) // 表示通过aop框架暴露该代理对象,AopContext能够访问
@MapperScan("cn.liangqinghai.study.spring.cloud.project.**.mapper")
@EnableAsync // 线程异步
@Import(SecurityImportBeanDefinitionRegistrar::class, OAuth2FeignConfig::class, ApplicationConfig::class)
annotation class EnableCustomConfig