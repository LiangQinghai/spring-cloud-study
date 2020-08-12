package cn.liangqinghai.study.spring.cloud.project.common.security.config

import org.springframework.beans.factory.support.BeanDefinitionBuilder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar
import org.springframework.core.type.AnnotationMetadata
import org.springframework.util.StringUtils

class SecurityImportBeanDefinitionRegistrar : ImportBeanDefinitionRegistrar {

    override fun registerBeanDefinitions(importingClassMetadata: AnnotationMetadata, registry: BeanDefinitionRegistry) {

        super.registerBeanDefinitions(importingClassMetadata, registry)
        val clazz = ResourceServerConfig::class.java
        val beanName = StringUtils.uncapitalize(clazz.simpleName)

        val beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz)
        registry.registerBeanDefinition(beanName, beanDefinitionBuilder.beanDefinition)

    }
}