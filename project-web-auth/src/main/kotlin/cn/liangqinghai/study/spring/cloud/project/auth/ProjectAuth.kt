package cn.liangqinghai.study.spring.cloud.project.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = ["cn.liangqinghai.study.spring.cloud"])
open class ProjectAuth

fun main(args: Array<String>) {

    runApplication<ProjectAuth>(*args)

}