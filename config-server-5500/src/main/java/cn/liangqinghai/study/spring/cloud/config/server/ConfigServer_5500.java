package cn.liangqinghai.study.spring.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author LiangQinghai
 * @title ConfigServer_5500
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 20:43
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer_5500 {

    public static void main(String[] args) {

        SpringApplication.run(ConfigServer_5500.class, args);

    }

}
