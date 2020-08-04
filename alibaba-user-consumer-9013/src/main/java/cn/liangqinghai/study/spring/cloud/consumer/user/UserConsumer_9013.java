package cn.liangqinghai.study.spring.cloud.consumer.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
@EnableDiscoveryClient
public class UserConsumer_9013 {

    public static void main(String[] args) {

        SpringApplication.run(UserConsumer_9013.class, args);

    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }

}
