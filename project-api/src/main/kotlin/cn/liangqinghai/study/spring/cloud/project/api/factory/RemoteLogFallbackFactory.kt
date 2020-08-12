package cn.liangqinghai.study.spring.cloud.project.api.factory

import cn.liangqinghai.study.spring.cloud.project.api.RemoteLogService
import cn.liangqinghai.study.spring.cloud.project.api.domain.SysOperLog
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import feign.hystrix.FallbackFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RemoteLogFallbackFactory : FallbackFactory<RemoteLogService> {

    override fun create(cause: Throwable?): RemoteLogService {
        log.error("日志服务调用失败: {}", cause?.message)
        return object : RemoteLogService {
            override fun saveLog(sysOperLog: SysOperLog?): ResultDto<Boolean?>? {
                return null
            }

            override fun saveLoginInfo(username: String?, status: String?, message: String?): ResultDto<Boolean?>? {
                return null
            }

        }
    }

    companion object {

        private val log = LoggerFactory.getLogger(RemoteLogFallbackFactory::class.java)

    }

}