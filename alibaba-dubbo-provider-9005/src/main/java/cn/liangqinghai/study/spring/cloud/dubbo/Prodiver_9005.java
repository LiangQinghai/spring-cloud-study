package cn.liangqinghai.study.spring.cloud.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

/**
 * @author LiangQinghai
 * @title Prodiver_9005
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/7 10:22
 */
@SpringBootApplication(exclude = {FeignAutoConfiguration.class})
@EnableDiscoveryClient
public class Prodiver_9005 {

    public static void main(String[] args) {

        SpringApplication.run(Prodiver_9005.class, args);

    }

}
