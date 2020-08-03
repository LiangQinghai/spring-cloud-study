package cn.liangqinghai.study.spring.cloud.consumer.user.feign;

import cn.liangqinghai.study.spring.cloud.common.dto.ResultDto;
import cn.liangqinghai.study.spring.cloud.common.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author LiangQinghai
 * @title UserFeign
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 16:49
 */
@Component
@FeignClient(value = "user-provider")
public interface UserFeign {

    @GetMapping("/cloud/user/list")
    ResultDto<List<User>> list();

}
