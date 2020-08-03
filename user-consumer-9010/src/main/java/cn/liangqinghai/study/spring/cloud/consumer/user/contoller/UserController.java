package cn.liangqinghai.study.spring.cloud.consumer.user.contoller;

import cn.liangqinghai.study.spring.cloud.common.dto.ResultDto;
import cn.liangqinghai.study.spring.cloud.common.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author LiangQinghai
 * @title UserController
 * @projectName spring-cloud-study
 * @description
 * @date 2020/8/3 15:15
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/list")
    public ResultDto<List<User>> list() {

        return restTemplate.exchange("", HttpMethod.GET, null, new ParameterizedTypeReference<ResultDto<List<User>>>() {
        }).getBody();

    }

    @GetMapping("/listServer")
    public ResultDto<List<String>> listServer() {

        return ResultDto.ok(discoveryClient.getServices());

    }

}
