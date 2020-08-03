package cn.liangqinghai.study.spring.cloud.consumer.user.feign.impl;

import cn.liangqinghai.study.spring.cloud.consumer.user.feign.UserFeignHystrix;
import org.springframework.stereotype.Component;

/**
 * @author LiangQinghai
 * @title UserFeignHystrixImpl
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 19:16
 */
@Component
public class UserFeignHystrixImpl implements UserFeignHystrix {
    @Override
    public String error() {
        return "出错啦";
    }
}
