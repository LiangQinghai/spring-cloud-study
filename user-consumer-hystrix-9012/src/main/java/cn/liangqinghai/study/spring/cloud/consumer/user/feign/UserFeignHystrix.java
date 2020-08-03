package cn.liangqinghai.study.spring.cloud.consumer.user.feign;

import cn.liangqinghai.study.spring.cloud.consumer.user.feign.impl.UserFeignHystrixImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LiangQinghai
 * @title UserFeignHystrix
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 17:58
 */
@FeignClient(value = "USER-PROVIDER-HYSTRIX", fallback = UserFeignHystrixImpl.class)
public interface UserFeignHystrix {

    @GetMapping("/cloud/user/hystrix")
    String error();

}
