package cn.liangqinghai.study.spring.cloud.project.api.factory

import cn.liangqinghai.study.spring.cloud.project.api.RemoteUserService
import cn.liangqinghai.study.spring.cloud.project.api.model.UserInfo
import cn.liangqinghai.study.spring.cloud.project.common.core.utils.dto.ResultDto
import feign.hystrix.FallbackFactory
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory

@Component
class RemoteUserFallbackFactory : FallbackFactory<RemoteUserService> {

    override fun create(cause: Throwable?): RemoteUserService {
        log.error("用户调用服务失败: {}", cause?.message)
        return object : RemoteUserService {
            override fun getUserInfo(username: String?): ResultDto<UserInfo?>? {
                return null
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(RemoteUserFallbackFactory::class.java)
    }

}