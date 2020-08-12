package cn.liangqinghai.study.spring.cloud.project.common.security.annos

import org.springframework.cloud.openfeign.EnableFeignClients
import kotlin.reflect.KClass

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@EnableFeignClients
annotation class EnableCustomFeignClients(
    vararg val value: String = [],
    val basePackages: Array<String> = ["cn.liangqinghai"],
    val basePackageClasses: Array<KClass<*>> = [],
    val defaultConfiguration: Array<KClass<*>> = [],
    val clients: Array<KClass<*>> = []
)