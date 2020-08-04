package cn.liangqinghai.study.spring.cloud.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangQinghai
 * @title ConfigController
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/4 19:14
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.info}")
    private String info;

    @GetMapping("/config/info")
    public String configInfo() {

        return info;

    }

}
