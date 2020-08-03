package cn.liangqinghai.study.spring.cloud.provider.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author LiangQinghai
 * @title UserProvider_9001
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 14:39
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableCircuitBreaker
@EnableDiscoveryClient
public class UserProvider_9002 {

    public static void main(String[] args) {

        SpringApplication.run(UserProvider_9002.class, args);

    }

}
