package cn.liangqinghai.study.spring.cloud.dubbo.service.impl;

import cn.liangqinghai.study.spring.cloud.common.models.User;
import cn.liangqinghai.study.spring.cloud.common.repos.UserRepos;
import cn.liangqinghai.study.spring.cloud.dubbo.service.IUserService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author LiangQinghai
 * @title UserServiceImpl
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/7 10:24
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements IUserService {
    @Override
    public List<User> list() {
        return UserRepos.users;
    }
}
