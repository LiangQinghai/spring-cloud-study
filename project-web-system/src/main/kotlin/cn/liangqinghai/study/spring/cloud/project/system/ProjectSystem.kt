package cn.liangqinghai.study.spring.cloud.project.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = ["cn.liangqinghai.study.spring.cloud"])
class ProjectSystem

fun main(args: Array<String>) {

    runApplication<ProjectSystem>(*args)

}