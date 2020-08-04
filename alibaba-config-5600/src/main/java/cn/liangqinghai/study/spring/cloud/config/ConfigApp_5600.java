package cn.liangqinghai.study.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiangQinghai
 * @title ConfigApp_5600
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/4 19:12
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigApp_5600 {

    public static void main(String[] args) {

        SpringApplication.run(ConfigApp_5600.class, args);

    }

}
