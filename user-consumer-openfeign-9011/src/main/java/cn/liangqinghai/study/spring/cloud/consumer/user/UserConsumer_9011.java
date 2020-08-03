package cn.liangqinghai.study.spring.cloud.consumer.user;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author LiangQinghai
 * @title UserConsumer_9010
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 15:14
 */
@SpringBootApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class UserConsumer_9011 {

    public static void main(String[] args) {

        SpringApplication.run(UserConsumer_9011.class, args);

    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

}
