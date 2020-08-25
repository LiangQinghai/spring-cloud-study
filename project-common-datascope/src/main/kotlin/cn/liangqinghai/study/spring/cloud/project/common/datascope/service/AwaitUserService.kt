package cn.liangqinghai.study.spring.cloud.project.common.datascope.service

import cn.liangqinghai.study.spring.cloud.project.api.RemoteUserService
import cn.liangqinghai.study.spring.cloud.project.api.model.UserInfo
import cn.liangqinghai.study.spring.cloud.project.common.security.utils.SecurityUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 同步调用用户服务
 * @author LiangQinghai
 */
@Service
class AwaitUserService {

    companion object {

        private val log = LoggerFactory.getLogger(AwaitUserService::class.java)

    }

    @Autowired
    val remoteUserService: RemoteUserService? = null

    /**
     * 查询用户信息
     *
     * @return
     */
    fun info(): UserInfo? {

        val username = SecurityUtils.username

        val userInfo = remoteUserService?.getUserInfo(username)

        if (userInfo == null || userInfo.data == null) {
            log.info("数据权限范围查询用户: {} 不存在", username)
            return null
        }

        return userInfo.data

    }

}