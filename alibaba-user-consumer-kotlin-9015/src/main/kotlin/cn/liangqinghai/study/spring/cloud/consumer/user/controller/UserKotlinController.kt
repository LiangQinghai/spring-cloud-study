package cn.liangqinghai.study.spring.cloud.consumer.user.controller

import cn.liangqinghai.study.spring.cloud.common.dto.ResultDto
import cn.liangqinghai.study.spring.cloud.common.models.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping(path = ["/api/user"])
class UserKotlinController {

    @Autowired
    val restTemplate: RestTemplate? = null

    @Autowired
    val discoveryClient: DiscoveryClient? = null

    @GetMapping(path = ["/list"])
    fun list(): ResultDto<List<User>>? {

        return restTemplate?.exchange("http://alibaba-user-provider/cloud/user/list", HttpMethod.GET, null, object : ParameterizedTypeReference<ResultDto<List<User>>>() {})
                ?.body

    }

}