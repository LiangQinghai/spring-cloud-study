package cn.liangqinghai.study.spring.cloud.provider.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiangQinghai
 * @title UserController
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 14:41
 */
@RestController
@RequestMapping("/cloud/user")
public class UserController {

    @GetMapping("/hystrix")
    @HystrixCommand(fallbackMethod = "errorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String error() {

        int i = 10 / 0;

        return "There's something wrong.";

    }

    public String errorHandler() {

        return "Please try again.";

    }

}
