package cn.liangqinghai.study.spring.cloud.project.auth.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/oauth")
class UserController {

    @RequestMapping("/user")
    fun user(user: Principal): Principal {

        return user

    }

}