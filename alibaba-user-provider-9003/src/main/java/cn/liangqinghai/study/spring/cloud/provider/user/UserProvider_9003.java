package cn.liangqinghai.study.spring.cloud.provider.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiangQinghai
 * @title UserProvider_9000
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 14:39
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserProvider_9003 {

    public static void main(String[] args) {

        SpringApplication.run(UserProvider_9003.class, args);

    }

}
