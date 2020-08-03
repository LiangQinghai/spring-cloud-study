package cn.liangqinghai.study.spring.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

/**
 * @author LiangQinghai
 * @title Gateway_5000
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 19:42
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Gateway_5000 {

    public static void main(String[] args) {

        SpringApplication.run(Gateway_5000.class, args);

    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//
//        RouteLocatorBuilder.Builder routes = builder.routes();
//
//        routes.route("my_route", r -> r.path("/goto/github")
//                                            .uri("https://github.com/"));
//        return routes.build();
//
//    }

}
