package cn.liangqinghai.study.spring.cloud.project.api

import cn.liangqinghai.study.spring.cloud.project.api.factory.RemoteUserFallbackFactory
import cn.liangqinghai.study.spring.cloud.project.api.model.UserInfo
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.ServiceNameConstants
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory::class)
interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = ["/user/info/{username}"])
    fun getUserInfo(@PathVariable("username") username: String?): ResultDto<UserInfo?>?

}