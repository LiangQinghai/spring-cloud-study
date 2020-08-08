package cn.liangqinghai.study.spring.cloud.project.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiangQinghai
 * @title ProjectGateway
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/8 16:23
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "cn.liangqinghai.study" ,exclude = {DataSourceAutoConfiguration.class})
public class ProjectGateway {

    public static void main(String[] args) {

        SpringApplication.run(ProjectGateway.class, args);

    }

}
