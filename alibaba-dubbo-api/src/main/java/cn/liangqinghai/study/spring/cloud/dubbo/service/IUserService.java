package cn.liangqinghai.study.spring.cloud.dubbo.service;

import cn.liangqinghai.study.spring.cloud.common.models.User;

import java.util.List;

/**
 * @author LiangQinghai
 * @title IUserService
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/7 10:10
 */
public interface IUserService {

    /**
     * list
     *
     * @return
     */
    List<User> list();

}
