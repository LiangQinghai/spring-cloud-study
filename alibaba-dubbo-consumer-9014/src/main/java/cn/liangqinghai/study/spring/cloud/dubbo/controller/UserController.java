package cn.liangqinghai.study.spring.cloud.dubbo.controller;

import cn.liangqinghai.study.spring.cloud.common.dto.ResultDto;
import cn.liangqinghai.study.spring.cloud.common.models.User;
import cn.liangqinghai.study.spring.cloud.dubbo.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiangQinghai
 * @title UserController
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/7 11:04
 */
@RestController
public class UserController {

    @Reference(version = "1.0.0")
    private IUserService userService;

    @GetMapping("/")
    public ResultDto<List<User>> users() {

        return ResultDto.ok(userService.list());

    }

}
