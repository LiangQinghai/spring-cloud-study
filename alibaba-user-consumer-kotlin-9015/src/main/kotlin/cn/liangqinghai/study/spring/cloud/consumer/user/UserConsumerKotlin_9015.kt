package cn.liangqinghai.study.spring.cloud.consumer.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableDiscoveryClient
class UserConsumerKotlin_9015

fun main(args : Array<String>) {

    runApplication<UserConsumerKotlin_9015>(*args)

}
