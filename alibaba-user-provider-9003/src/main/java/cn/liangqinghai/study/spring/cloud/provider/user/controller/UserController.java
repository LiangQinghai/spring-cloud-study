package cn.liangqinghai.study.spring.cloud.provider.user.controller;

import cn.liangqinghai.study.spring.cloud.common.dto.ResultDto;
import cn.liangqinghai.study.spring.cloud.common.models.User;
import cn.liangqinghai.study.spring.cloud.common.repos.UserRepos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @GetMapping("/list")
    public ResultDto<List<User>> list() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultDto.ok(UserRepos.users);

    }

}
