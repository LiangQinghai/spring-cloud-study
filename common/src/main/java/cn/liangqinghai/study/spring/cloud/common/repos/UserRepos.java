package cn.liangqinghai.study.spring.cloud.common.repos;

import cn.liangqinghai.study.spring.cloud.common.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangQinghai
 * @title UserRepos
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 15:09
 */
public class UserRepos {

    public static final List<User> users = new ArrayList<>();

    static {

        users.add(User.builder()
                .id("1")
                .username("one")
                .password("123456")
                .build());

        users.add(User.builder()
                .id("2")
                .username("two")
                .password("123456")
                .build());

        users.add(User.builder()
                .id("3")
                .username("three")
                .password("123456")
                .build());

        users.add(User.builder()
                .id("4")
                .username("four")
                .password("123456")
                .build());

        users.add(User.builder()
                .id("5")
                .username("five")
                .password("123456")
                .build());

    }

}
