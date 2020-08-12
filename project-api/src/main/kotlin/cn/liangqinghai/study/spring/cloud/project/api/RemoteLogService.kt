package cn.liangqinghai.study.spring.cloud.project.api

import cn.liangqinghai.study.spring.cloud.project.api.domain.SysOperLog
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.constant.ServiceNameConstants
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE)
interface RemoteLogService {

    @PostMapping(value = ["/operlog"])
    fun saveLog(@RequestBody sysOperLog: SysOperLog?): ResultDto<Boolean?>?

    @PostMapping(value = ["logininfo"])
    fun saveLoginInfo(@RequestParam("username") username: String?, @RequestParam("status") status: String?, @RequestParam("message") message: String?): ResultDto<Boolean?>?

}