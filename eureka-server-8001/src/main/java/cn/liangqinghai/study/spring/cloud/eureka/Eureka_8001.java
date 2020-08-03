package cn.liangqinghai.study.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LiangQinghai
 * @title Eureka_8001
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 11:47
 */
@SpringBootApplication
@EnableEurekaServer
public class Eureka_8001 {

    public static void main(String[] args) {

        SpringApplication.run(Eureka_8001.class, args);

    }

}
