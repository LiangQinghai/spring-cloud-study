package cn.liangqinghai.study.spring.cloud.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

/**
 * @author LiangQinghai
 * @title Consumer_9014
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/7 11:03
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {FeignAutoConfiguration.class})
public class Consumer_9014 {

    public static void main(String[] args) {

        SpringApplication.run(Consumer_9014.class, args);

    }

}
